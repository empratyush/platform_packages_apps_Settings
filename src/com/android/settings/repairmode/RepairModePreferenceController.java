package com.android.settings.repairmode;
 
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.os.PowerManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.image.DynamicSystemManager;
import android.provider.Settings;

import com.android.internal.widget.LockPatternUtils;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.R.string;
import com.android.settings.R;
 
 public class RepairModePreferenceController extends BasePreferenceController {
 
    UserManager userManager =  mContext.getSystemService(UserManager.class);
    DynamicSystemManager dsm = mContext.getSystemService(DynamicSystemManager.class);
    public static final String REPAIR_MODE_KEY = "repairmode.lock";
    public static final String REBOOT_REASON = "Repair Mode";

    public RepairModePreferenceController(Context context, String str) {
        super(context, str);
    }
 
    public int getAvailabilityStatus() {
        if (!LockPatternUtils.isRepairModeSupported(this.mContext)) {
            return UNSUPPORTED_ON_DEVICE;
        }
        if (userManager.hasUserRestriction(UserManager.DISALLOW_SAFE_BOOT)) {
            return DISABLED_FOR_USER;
        }
        boolean isInRepairMode = LockPatternUtils.isRepairModeActive(mContext);
        boolean canUserEnterRepairMode = LockPatternUtils.canUserEnterRepairMode(mContext, getCurrentUserInfo());
        return (isInRepairMode || canUserEnterRepairMode) ? AVAILABLE : DISABLED_FOR_USER;
    }

    public CharSequence getSummary() {
        if (LockPatternUtils.isRepairModeActive(mContext)) {
            return mContext.getString(R.string.repair_mode_active_summary);
        }
        return mContext.getString(R.string.repair_mode_summary);
    }
 
    private UserInfo getCurrentUserInfo() {
       return userManager.getUserInfo(UserHandle.myUserId());
    }
 }
 