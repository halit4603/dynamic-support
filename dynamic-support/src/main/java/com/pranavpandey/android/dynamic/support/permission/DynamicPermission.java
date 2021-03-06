/*
 * Copyright 2018 Pranav Pandey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pranavpandey.android.dynamic.support.permission;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A model class to hold the permission details that will be used
 * by the app. It will be used internally by the {@link DynamicPermissions}.
 */
public class DynamicPermission implements Parcelable {

    /**
     * The permission string.
     */
    private String permission;

    /**
     * Icon used by this permission.
     */
    private Drawable icon;

    /**
     * Title used by this permission.
     */
    private String title;

    /**
     * Subtitle used by this permission.
     */
    private String subtitle;

    /**
     * Description used by this permission.
     */
    private String description;

    /**
     * {@code true} if the permission is dangerous.
     *
     * @see <a href="https://developer.android.com/guide/topics/permissions/requesting.html#normal-dangerous">
     *      Normal and Dangerous Permissions</a>
     */
    private boolean dangerous;

    /**
     * {@code true} if the permission is granted.
     */
    private boolean granted;

    /**
     * {@code true} if request this permission again.
     *
     * @see android.support.v4.app.ActivityCompat#shouldShowRequestPermissionRationale(Activity, String)
     */
    private boolean askAgain;

    /**
     * Default constructor to initialize the dynamic permission.
     */
    public DynamicPermission() { }

    /**
     * Constructor to initialize an object of this class.
     *
     * @param permission The permission string.
     * @param description The description for this permission.
     */
    public DynamicPermission(@NonNull String permission, @Nullable String description) {
        this.permission = permission;
        this.description = description;
    }

    /**
     * Constructor to initialize an object of this class.
     *
     * @param permission The permission string.
     * @param icon The icon used for this permission.
     * @param title The title for this permission.
     * @param subtitle The subtitle for this permission.
     */
    public DynamicPermission(@NonNull String permission, @Nullable Drawable icon,
                             @NonNull String title, @Nullable String subtitle) {
        this.permission = permission;
        this.icon = icon;
        this.title = title;
        this.subtitle = subtitle;
    }

    /**
     * @return The permission string.
     */
    public @NonNull String getPermission() {
        return permission;
    }

    /**
     * Sets the permission string.
     *
     * @param permission The permission to be set.
     */
    public void setPermission(@NonNull String permission) {
        this.permission = permission;
    }

    /**
     * @return The icon used by this permission.
     */
    public @Nullable Drawable getIcon() {
        return icon;
    }

    /**
     * Sets the icon for this permission.
     *
     * @param icon The icon to be set.
     */
    public void setIcon(@Nullable Drawable icon) {
        this.icon = icon;
    }

    /**
     * @return The title used by this permission.
     */
    public @Nullable String getTitle() {
        return title;
    }

    /**
     * Sets the title for this permission.
     *
     * @param title The title to be set.
     */
    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    /**
     * @return The subtitle used by this permission.
     */
    public @Nullable String getSubtitle() {
        return subtitle;
    }

    /**
     * Sets the subtitle for this permission.
     *
     * @param subtitle The subtitle to be set.
     */
    public void setSubtitle(@Nullable String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * @return The description used by this permission.
     */
    public @Nullable String getDescription() {
        return description;
    }

    /**
     * Sets the description for this permission.
     *
     * @param description The description to be set.
     */
    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    /**
     * @return {@code true} if the permission is dangerous.
     */
    public boolean isDangerous() {
        return dangerous;
    }

    /**
     * Sets this permission as dangerous or normal.
     *
     * @param dangerous {@code true} if the permission is
     *                  dangerous.
     */
    public void setDangerous(boolean dangerous) {
        this.dangerous = dangerous;
    }

    /**
     * @return {@code true} if the permission is granted.
     */
    public boolean isGranted() {
        return granted;
    }

    /**
     * Sets this permission as granted or denied.
     *
     * @param granted {@code true} if the permission is
     *                granted.
     */
    public void setGranted(boolean granted) {
        this.granted = granted;
    }

    /**
     * @return {@code true} if request this permission again.
     */
    public boolean isAskAgain() {
        return askAgain;
    }

    /**
     * Sets this permission to request again or not.
     *
     * @param askAgain {@code true} if request this
     *                 permission again.
     */
    public void setAskAgain(boolean askAgain) {
        this.askAgain = askAgain;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(permission);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(description);
        dest.writeByte((byte) (dangerous ? 1 : 0));
        dest.writeByte((byte) (granted ? 1 : 0));
        dest.writeByte((byte) (askAgain ? 1 : 0));
        Bitmap bitmap = ((BitmapDrawable) icon).getBitmap();
        dest.writeParcelable(bitmap, flags);
    }

    /**
     * Parcelable creator to create from parcel.
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public DynamicPermission createFromParcel(Parcel in) {
            return new DynamicPermission(in);
        }

        public DynamicPermission[] newArray(int size) {
            return new DynamicPermission[size];
        }
    };

    /**
     * De-parcel {@link DynamicPermission} object.
     *
     * @param in The parcel to read the values.
     */
    public DynamicPermission(Parcel in) {
        this.permission = in.readString();
        this.title = in.readString();
        this.subtitle = in.readString();
        this.description = in.readString();
        this.dangerous = in.readByte() != 0;
        this.granted = in.readByte() != 0;
        this.askAgain = in.readByte() != 0;
        Bitmap bitmap = in.readParcelable(getClass().getClassLoader());
        this.icon = new BitmapDrawable(Resources.getSystem(), bitmap);
    }
}
