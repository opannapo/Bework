package napodev.framework.bework.utils.helper.permissions;

/**
 * Created by opannapo on 8/21/17.
 */
public class PermissionEntity {
    private int requestCode;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }
}
