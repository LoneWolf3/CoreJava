package com.sac.threads;

/**
 * 
 * Signal can be missed if doNotify called before doWait. So doNotify should
 * mark somehow that it was called. The usual way for this is using a variable.
 * Uncomment all usages of wasSignalled and the signal will not be missed.
 * 
 * uncomment every thing below to correct missed signal scenario.
 * 
 * @author ssachdev
 *
 */
public class MissedSignal {

	//boolean wasSignalled = false;

	public void doWait() {
		synchronized (this) {
			//if (!wasSignalled) {

				try {
					this.wait();
				} catch (InterruptedException e) {
				}
			}
		}
//	}

	public void doNotify() {
		synchronized (this) {
			//wasSignalled = true;

			this.notify();
		}

	}
}