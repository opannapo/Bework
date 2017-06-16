package napodev.framework.bework.corebase.model.parcel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by opannapo on 4/28/17.
 */
public abstract class BaseParcelable implements Parcelable {

    public BaseParcelable() {

    }

    public BaseParcelable(Parcel in) {
        ParcelInject.read(this, in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelInject.write(this, dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public BaseParcelable parse(Object o) {
        return this;
    }

}
