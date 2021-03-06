package de.flapdoodle.embed.process.runtime;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT;
import de.flapdoodle.embed.process.collections.Collections;
import de.flapdoodle.embed.process.config.ISupportConfig;
import de.flapdoodle.embed.process.config.process.ProcessConfig;
import de.flapdoodle.embed.process.distribution.Platform;
import de.flapdoodle.embed.process.io.IStreamProcessor;
import de.flapdoodle.embed.process.io.LogWatchStreamProcessor;
import de.flapdoodle.embed.process.io.Processors;
import de.flapdoodle.embed.process.io.StreamToLineProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.SourceVersion;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created By artemvlasov on 27/12/2017
 **/
public class Processes {

    private static Logger logger = LoggerFactory.getLogger(ProcessControl.class);

    private static final PidHelper PID_HELPER;

    static {
        // Comparing with the string value to avoid a strong dependency on JDK 9
        if (SourceVersion.latest().toString().equals( "RELEASE_9" )) {
            PID_HELPER = PidHelper.JDK_9;
        }
        else {
            PID_HELPER = PidHelper.LEGACY;
        }
    }

    private Processes() {
        // no instance
    }

    public static Long processId(Process process) {
        return PID_HELPER.getPid(process);
    }

    private static Long unixLikeProcessId(Process process) {
        Class<?> clazz = process.getClass();
        try {
            if (clazz.getName().equals("java.lang.UNIXProcess")) {
                Field pidField = clazz.getDeclaredField("pid");
                pidField.setAccessible(true);
                Object value = pidField.get(process);
                if (value instanceof Integer) {
                    logger.debug("Detected pid: {}", value);
                    return ((Integer) value).longValue();
                }
            }
        } catch (SecurityException sx) {
            sx.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @see http://www.golesny.de/p/code/javagetpid
     *
     * @return
     */
    private static Long windowsProcessId(Process process) {
        if (process.getClass().getName().equals("java.lang.Win32Process")
                || process.getClass().getName().equals("java.lang.ProcessImpl")) {
            /* determine the pid on windows plattforms */
            try {
                Field f = process.getClass().getDeclaredField("handle");
                f.setAccessible(true);
                long handl = f.getLong(process);

                Kernel32 kernel = Kernel32.INSTANCE;
                WinNT.HANDLE handle = new WinNT.HANDLE();
                handle.setPointer(Pointer.createConstant(handl));
                int ret = kernel.GetProcessId(handle);
                logger.debug("Detected pid: {}", ret);
                return Long.valueOf(ret);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean killProcess(ISupportConfig support, Platform platform, IStreamProcessor output, long pid) {
        if (platform.isUnixLike()) {
            return ProcessControl.executeCommandLine(support, "[kill process]",
                    new ProcessConfig(Collections.newArrayList("kill", "-2", "" + pid), output));
        }
        return false;
    }

    public static boolean termProcess(ISupportConfig support,Platform platform, IStreamProcessor output, long pid) {
        if (platform.isUnixLike()) {
            return ProcessControl.executeCommandLine(support, "[term process]",
                    new ProcessConfig(Collections.newArrayList("kill", "" + pid), output));
        }
        return false;
    }

    public static boolean tryKillProcess(ISupportConfig support,Platform platform, IStreamProcessor output, long pid) {
        if (platform == Platform.Windows) {
            return ProcessControl.executeCommandLine(support, "[taskkill process]",
                    new ProcessConfig(Collections.newArrayList("taskkill", "/F", "/pid", "" + pid), output));
        }
        return false;
    }

    public static boolean isProcessRunning(Platform platform, long pid) {

        try {
            final Process pidof;
            if (platform.isUnixLike()) {
                pidof = Runtime.getRuntime().exec(
                        new String[] { "kill", "-0", "" + pid });
                return pidof.waitFor() == 0;
            } else {
                // windows
                // process might be in either NOT RESPONDING due to
                // firewall blocking, or could be RUNNING
                final String[] cmd = { "tasklist.exe",
                        "/FI", "PID eq " + pid ,"/FO", "CSV" };
                logger.trace("Command: {}", Arrays.asList(cmd));
                ProcessBuilder processBuilder = ProcessControl
                        .newProcessBuilder(Arrays.asList(cmd), true);
                Process process = processBuilder.start();
                // look for the PID in the output, pass it in for 'success' state
                LogWatchStreamProcessor logWatch = new LogWatchStreamProcessor(""+pid,
                        new HashSet<String>(), StreamToLineProcessor.wrap(Processors.silent()));
                Processors.connect(new InputStreamReader(process.getInputStream()), logWatch);
                logWatch.waitForResult(2000);
                logger.trace("logWatch output: {}", logWatch.getOutput());
                return logWatch.isInitWithSuccess();
            }

        } catch (IOException e) {
            logger.error("Trying to get process status", e);
            e.printStackTrace();

        } catch (InterruptedException e) {
            logger.error("Trying to get process status", e);
            e.printStackTrace();
        }
        return false;
    }

    private enum PidHelper {

        JDK_9 {
            @Override
            Long getPid(Process process) {
                try {
                    // Invoking via reflection to avoid a strong dependency on JDK 9
                    Method getPid = Process.class.getMethod("pid");
                    return (Long) getPid.invoke(process);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        },
        LEGACY {
            @Override
            Long getPid(Process process) {
                Long pid=unixLikeProcessId(process);
                if (pid==null) {
                    pid=windowsProcessId(process);
                }
                return pid;
            }
        };

        abstract Long getPid(Process process);
    }

}
