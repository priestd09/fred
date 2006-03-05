package freenet.pluginmanager;

import java.util.Date;

public class PluginInfoWrapper {
	// Parameters to make the object OTP
	private boolean fedPluginThread = false;
	// Public since only PluginHandler will know about it
	private String className;
	private Thread thread;
	private long start;
	private String threadName;
	private FredPlugin plug;
	private boolean isPproxyPlugin;
	private String filename;
	//public String 
	
	public PluginInfoWrapper(FredPlugin plug, Thread ps, String filename) {
		if (fedPluginThread) return;
		className = plug.getClass().toString();
		thread = ps;
		this.plug = plug;
		this.filename = filename;
		threadName = "p" + className.replaceAll("^class ", "") + "_" + ps.hashCode();
		start = System.currentTimeMillis();
		ps.setName(threadName);
		fedPluginThread = true;
		isPproxyPlugin = (plug instanceof FredPluginHTTP);
	}
	
	public String toString() {
		return "ID: \"" +threadName + "\", Name: "+ className +", Started: " + (new Date(start)).toString();
	}
	
	public String getThreadName() {
		return threadName;
	}
	
	public long getStarted() {
		return start;
	}
	
	public String getPluginClassName(){
		return plug.getClass().getName().toString();
	}

	public void stopPlugin() {
		plug.terminate();
		thread.interrupt();
	}
	
	public boolean sameThread(Thread t){
		return (t == thread);
	}

	public boolean isPproxyPlugin() {
		return isPproxyPlugin;
	}

	public String getFilename() {
		return filename;
	}
	
}
