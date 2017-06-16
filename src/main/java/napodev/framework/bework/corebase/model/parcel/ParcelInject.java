package napodev.framework.bework.corebase.model.parcel;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

import napodev.framework.bework.utils.Log;

/**
 * Created by opannapo on 4/28/17.
 */
public class ParcelInject {
    public static final int STRING = 1;
    public static final int STRING_ARRAY = 2;
    public static final int INT = 3;
    public static final int INT_ARRAY = 4;
    public static final int LONG = 5;
    public static final int LONG_ARRAY = 6;
    public static final int DOUBLE = 7;
    public static final int DOUBLE_ARRAY = 8;
    public static final int JSONOBJECT = 9;
    public static final int JSONARRAY = 10;
    public static final int PARCELABLE = 11;
    public static final int BOOLEAN = 12;

    public static void write(Object source, Parcel dest, int flag) {
        String TAG = source.getClass().getSimpleName();
        Log.d(TAG + " ParcelInject-write");

        Field[] field = source.getClass().getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            Field f = field[i];
            if (f.isAnnotationPresent(Entity.class)) {
                int anoValue = f.getAnnotation(Entity.class).value();
                Log.d(TAG + " isAnnotationPresent Entity " + f.getName() + " type " + f.getType() + " " + anoValue);
                try {
                    switch (anoValue) {
                        case INT:
                            dest.writeInt((int) f.get(source));
                            break;
                        case INT_ARRAY:
                            dest.writeIntArray((int[]) f.get(source));
                            break;
                        case STRING:
                            dest.writeString((String) f.get(source));
                            break;
                        case STRING_ARRAY:
                            dest.writeStringArray((String[]) f.get(source));
                            break;
                        case DOUBLE:
                            dest.writeDouble((double) f.get(source));
                            break;
                        case DOUBLE_ARRAY:
                            dest.writeDoubleArray((double[]) f.get(source));
                            break;
                        case LONG:
                            dest.writeLong((long) f.get(source));
                            break;
                        case LONG_ARRAY:
                            dest.writeLongArray((long[]) f.get(source));
                            break;
                        case JSONOBJECT:
                            if (f.get(source) == null) {
                                dest.writeByte((byte) (0x00));
                            } else {
                                dest.writeByte((byte) (0x01));
                                dest.writeString(f.get(source).toString());
                            }
                            break;
                        case JSONARRAY:
                            dest.writeString(String.valueOf(f.get(source)));
                            break;
                        case PARCELABLE:
                            dest.writeParcelable((Parcelable) f.get(source), flag);
                            break;
                        case BOOLEAN:
                            dest.writeByte((byte) (((boolean) f.get(source)) ? 0x01 : 0x00));
                            break;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d(TAG + "NOT Entity " + f.getName());
            }
        }
    }

    public static void read(Object source, Parcel in) {
        String TAG = source.getClass().getSimpleName();
        Log.d(TAG + " ParcelInject-read");

        Field[] field = source.getClass().getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            Field f = field[i];
            f.setAccessible(true);
            if (f.isAnnotationPresent(Entity.class)) {
                int anoValue = f.getAnnotation(Entity.class).value();
                Log.d(TAG + " isAnnotationPresent Entity " + f.getName() + " type " + f.getType() + " " + anoValue);
                try {
                    //f.set(source, in.readInt());
                    switch (anoValue) {
                        case INT:
                            f.set(source, in.readInt());
                            break;
                        case INT_ARRAY:
                            f.set(source, in.createIntArray());
                            break;
                        case STRING:
                            f.set(source, in.readString());
                            break;
                        case STRING_ARRAY:
                            f.set(source, in.createStringArray());
                            break;
                        case DOUBLE:
                            f.set(source, in.readDouble());
                            break;
                        case DOUBLE_ARRAY:
                            f.set(source, in.createDoubleArray());
                            break;
                        case LONG:
                            f.set(source, in.readLong());
                            break;
                        case LONG_ARRAY:
                            f.set(source, in.createLongArray());
                            break;
                        case JSONOBJECT:
                            try {
                                if (in.readByte() == 0x00) {
                                    f.set(source, null);
                                } else {
                                    f.set(source, new JSONObject(in.readString()));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            break;
                        case JSONARRAY:
                            f.set(source, in.readString());
                            break;
                        case PARCELABLE:
                            f.set(source, in.readParcelable(source.getClass().getClassLoader()));
                            break;
                        case BOOLEAN:
                            f.set(source, in.readByte() != 0x00);
                            break;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d(TAG + "NOT Entity " + f.getName());
            }
        }
    }
}
