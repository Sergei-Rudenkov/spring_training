package com.rudenkov.epam.meta;

/**
 * Created by sergei-rudenkov on 12.10.16.
 */
public class ProfilingController implements ProfilingControllerMBean {

    private boolean isEnabled = true;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
