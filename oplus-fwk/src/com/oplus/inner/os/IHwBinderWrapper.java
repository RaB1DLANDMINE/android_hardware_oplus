package com.oplus.inner.os;

public class IHwBinderWrapper {
    public static abstract class DeathRecipientWrapper {
        public DeathRecipientWrapper() {
        }

        public abstract void serviceDied(long cookie);
    }
}
