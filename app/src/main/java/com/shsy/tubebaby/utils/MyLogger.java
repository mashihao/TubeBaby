package com.shsy.tubebaby.utils;

import android.util.Log;

import com.shsy.tubebaby.BuildConfig;

import java.io.PrintWriter;
import java.util.HashMap;


/**
 * 日志
 */

public class MyLogger {
  private final static boolean sIsLoggerEnable = true;

  private final static String tag = "EhaiDriver";

  public static int logLevel = Log.VERBOSE;

  private static HashMap<String, MyLogger> sLoggerTable;
  private static boolean logWriteToFile = false;

  static {
    sLoggerTable = new HashMap<String, MyLogger>();
  }

  private PrintWriter pw = null;
  private String mClassName;

  private MyLogger(String name) {
    mClassName = name;
  }

  public static MyLogger getLogger(String className) {
    MyLogger classLogger = sLoggerTable.get(className);
    if (classLogger == null) {
      classLogger = new MyLogger(className);
      sLoggerTable.put(className, classLogger);
    }
    return classLogger;
  }

  private String getFunctionName() {
    StackTraceElement[] sts = Thread.currentThread().getStackTrace();

    if (sts == null) {
      return null;
    }

    for (StackTraceElement st : sts) {
      if (st.isNativeMethod()) {
        continue;
      }

      if (st.getClassName().equals(Thread.class.getName())) {
        continue;
      }

      if (st.getClassName().equals(this.getClass().getName())) {
        continue;
      }

      return "[ "
          + Thread.currentThread().getName()
          + ": "
          + st.getFileName()
          + ":"
          + st.getLineNumber()
          + " ]";
    }

    return null;
  }

  public void info(Object str) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    if (logWriteToFile) {
      writeLogToFile(str);
    }
    if (logLevel <= Log.INFO) {
      String name = getFunctionName();
      if (name != null) {
        Log.i(tag, name + " - " + str);
      } else {
        Log.i(tag, str.toString());
      }
    }
  }

  public void i(Object str) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    if (logWriteToFile) {
      writeLogToFile(str);
    }
    info(str);
  }

  public void verbose(Object str) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    if (logLevel <= Log.VERBOSE) {
      String name = getFunctionName();
      if (name != null) {
        Log.v(tag, name + " - " + str);
      } else {
        Log.v(tag, str.toString());
      }
    }
  }

  public void v(Object str) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    if (logWriteToFile) {
      writeLogToFile(str);
    }
    verbose(str);
  }

  public void warn(Object str) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    if (logLevel <= Log.WARN) {
      String name = getFunctionName();

      if (name != null) {
        Log.w(tag, name + " - " + str);
      } else {
        Log.w(tag, str.toString());
      }
    }
  }

  public void w(Object str) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    if (logWriteToFile) {
      writeLogToFile(str);
    }
    warn(str);
  }

  public void error(Object str) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    if (logLevel <= Log.ERROR) {

      String name = getFunctionName();
      if (name != null) {
        Log.e(tag, name + " - " + str);
      } else {
        Log.e(tag, str.toString());
      }
    }
  }

  public void error(Exception ex) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    if (logLevel <= Log.ERROR) {
      Log.e(tag, "error", ex);
    }
  }

  public void e(Object str) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    if (logWriteToFile) {
      writeLogToFile(str);
    }
    error(str);
  }

  public void e(Exception ex) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    if (logWriteToFile) {
      writeLogToFile(ex);
    }
    //error(ex);
  }

  public void e(String log, Throwable tr) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    String line = getFunctionName();
    if (sIsLoggerEnable) {
      Log.e(tag, "{Thread:"
          + Thread.currentThread().getName()
          + "}"
          + "["
          + mClassName
          + line
          + ":] "
          + log
          + "\n", tr);
      if (logWriteToFile) {
        writeLogToFile("{Thread:"
            + Thread.currentThread().getName()
            + "}"
            + "["
            + mClassName
            + line
            + ":] "
            + log
            + "\n"
            + Log.getStackTraceString(tr));
      }
    }
  }

  public void debug(Object str) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    if (logLevel <= Log.DEBUG) {
      String name = getFunctionName();
      if (name != null) {
        Log.d(tag, name + " - " + str);
      } else {
        Log.d(tag, str.toString());
      }
    }
  }

  public void d(Object str) {
    if (BuildConfig.APP_BUILD_TYPE) return;
    if (logWriteToFile) {
      writeLogToFile(str);
    }
    debug(str);
  }

  private void writeLogToFile(Object str) {
    Log.d("writeLogToFile", "writeLogToFile");
  }
}
