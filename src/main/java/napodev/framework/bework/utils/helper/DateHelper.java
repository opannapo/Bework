package napodev.framework.bework.utils.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by opannapo on 11/1/15.
 */
public final class DateHelper {
    private static SimpleDateFormat fromFormat;
    private static SimpleDateFormat toFormat;
    private static Date newDate;

    /**
     * ENUM TIME/DATE FORMAT
     **/
    public enum FORMAT {
        FORMAT_1 {
            public String toString() {
                return "dd MMM yyyy HH:mm";
            }
        },
        FORMAT_2 {
            public String toString() {
                return "dd MMM yyyy HH:mm:ss";
            }
        },
        FORMAT_3 {
            public String toString() {
                return "dd/MM/yyyy";
            }
        },
        FORMAT_4 {
            public String toString() {
                return "dd MMM yyyy";
            }
        },
        FORMAT_5 {
            public String toString() {
                return "dd-MMM-yyyy";
            }
        },
        FORMAT_6 {
            public String toString() {
                return "dd-MMM-yyyy HH:mm";
            }
        },
        FORMAT_7 {
            public String toString() {
                return "d-M-yyyy k:m";
            }
        },
        FORMAT_8 {
            public String toString() {
                return "MMM d, yyyy k:mm";
            }
        },
        FORMAT_9 {
            public String toString() {
                return "MMMM dd";
            }
        }, FORMAT_HH_MM {
            public String toString() {
                return "HH:mm";
            }
        }, HH_MM_SS_SSS {
            public String toString() {
                return "HH:mm:ss.SSS";
            }
        }, FORMAT_MMM_D_YYYY_HH_MM {
            public String toString() {
                return "MMM dd yyyy HH:mm";
            }
        },
        FORMAT_MMM_D_HH_MM {
            public String toString() {
                return "MMM dd HH:mm";
            }
        },
        FORMAT_YYYY {
            public String toString() {
                return "yyyy";
            }
        },
        FORMAT_M {
            public String toString() {
                return "M";
            }
        },
        FORMAT_D {
            public String toString() {
                return "d";
            }
        }
    }

    /**
     * GET MY TIMESTAMP
     **/
    public static int getMyTimeStampMilis() {
        int timeStamp = (int) System.currentTimeMillis();
        return timeStamp;
    }


    public static class TimeMillis {
        public static String asDateString(long timestamp, FORMAT format) {
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp);
                SimpleDateFormat sdf = new SimpleDateFormat(format.toString());
                sdf.setTimeZone(TimeZone.getDefault());
                Date currenTimeZone = calendar.getTime();
                return sdf.format(currenTimeZone);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public static Date asDate(long timestamp, FORMAT format) {
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp);
                Date currenTimeZone = calendar.getTime();
                return currenTimeZone;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public static long differenceAsMillis(long start, long end) {
            try {
                long difference = end - start;
                return difference;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        public static String differenceAsString(long start, long end) {
            try {
                long difference = end - start;

                boolean isMillisecond = difference < 1000;
                boolean isSecond = difference > 999;

                if (isSecond) {
                    float result = difference / 1000.0f;
                    return result + " s  (" + difference + ")";
                } else {
                    return difference + " ms";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }


}
