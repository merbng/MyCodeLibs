package com.app.merbng.mycodelibs.utils;

import android.util.Log;

/**
 * Log工具类
 */
public class LogUtil {
    public LogUtil() {
    }

    public static class log {
        public static final boolean show = true;
        public static final String Tag = "===日志===";
        public static String Cname = "";
        public static String Mname = "";

        public log() {
        }

        private static boolean shouldShow(int tag_level) {
            return (tag_level & -1) > 0;
        }

        protected static void getTrace() {
            StackTraceElement caller = (new Throwable()).fillInStackTrace().getStackTrace()[2];
            String className = caller.getClassName();
            className = className.substring(className.lastIndexOf(".") + 1);
            Cname = className;
            Mname = caller.getMethodName() + "->" + caller.getLineNumber() + ": ";
        }

        public static void v(String text) {
            if(shouldShow(2)) {
                getTrace();
                if(null == text) {
                    text = "null";
                }

                Log.v(Tag, Mname + text);
            }
        }

        public static void d(String text) {
            if(shouldShow(4)) {
                getTrace();
                if(null == text) {
                    text = "null";
                }

                Log.d(Tag, Cname + "->" + Mname + text);
            }
        }

        public static void d(int text) {
            d("" + text);
        }

        public static void d(float text) {
            d("" + text);
        }

        public static void d(double text) {
            d("" + text);
        }

        public static void d() {
            if(shouldShow(4)) {
                getTrace();
                Log.d(Tag, Tag + Mname + "");
            }
        }

        public static void d(String Tag, String text) {
            if(shouldShow(4)) {
                getTrace();
                Log.d(Tag, Cname + "->" + Mname + text);
            }
        }

        public static void d(String text, Exception e) {
            if(shouldShow(32)) {
                String tmp = Cname + "->" + Mname + text + ":" + e.toString();
                Log.d(Tag, tmp);
                e.printStackTrace();
            }
        }

        public static void i(String text) {
            if(shouldShow(8)) {
                getTrace();
                if(null == text) {
                    text = "null";
                }

                Log.i(Tag, Mname + text);
            }
        }

        public static void w(String text) {
            if(shouldShow(16)) {
                getTrace();
                if(null == text) {
                    text = "null";
                }

                Log.w(Tag, Mname + text);
            }
        }

        public static void e(String text) {
            if(shouldShow(32)) {
                getTrace();
                if(null == text) {
                    text = "null";
                }

                Log.e(Tag, Cname + "->" + Mname + text);
            }
        }

        public static void e() {
            if(shouldShow(32)) {
                getTrace();
                Log.e(Tag, Cname + "->" + Mname + "");
            }
        }

        public static void e(String text, Exception e) {
            if(shouldShow(32)) {
                String tmp = text + Mname + "err:" + e.toString();
                Log.e(Tag, tmp);
                e.printStackTrace();
            }
        }

        public static void e(String Tag, String text) {
            if(shouldShow(32)) {
                getTrace();
                Log.e(Tag, Cname + "->" + Mname + text);
            }
        }

        public static void e(String Tag, String text, Exception e) {
            if(shouldShow(32)) {
                getTrace();
                Log.e(Tag, Cname + "->" + Mname + text + " err:" + e.toString());
            }
        }
    }

    public static class avlog {
        public static boolean showAVLog = true;

        public avlog() {
        }

        public static void i(String text) {
            if(showAVLog) {
                LogUtil.log.i(text);
            }

        }

        public static void i(Object o) {
            if(showAVLog) {
                LogUtil.log.i("" + o);
            }

        }

        public static void d(String text) {
            if(showAVLog) {
                LogUtil.log.d(text);
            }

        }

        public static void e(String text) {
            if(showAVLog) {
                LogUtil.log.e(text);
            }

        }
    }
}
