/*
 * SoundInfoForm.java
 *
 * Created on 29 de junio de 2004, 19:34
 */

package com.grimo.me.product.midpsysinfo;



import javax.microedition.lcdui.Display;

import de.enough.sysinfo.MIDPSysInfoMIDlet;

/**
 * Collects information about the sound support of the device.
 * 
 * @author  Waldemar Baraldi <waldemar.baraldi@grimo-software.com>
 * @author  Robert Virkus <j2mepolish@enough.de> (architectural changes)
 * @author  Mark Schrijver <mark.schrijver@mobillion.nl> (additional system properties)
 */
public class MultiMediaInfoCollector extends InfoCollector {
    
    
    /** 
     * Creates a new instance of SoundInfoCollector 
     */
    public MultiMediaInfoCollector() {
        super();
    }
    
    /* (non-Javadoc)
	 * @see com.grimo.me.product.midpsysinfo.InfoCollector#collectInfos(com.grimo.me.product.midpsysinfo.MIDPSysInfoMIDlet, javax.microedition.lcdui.Display)
	 */
	public void collectInfos(MIDPSysInfoMIDlet midlet, Display display) {
        addInfo("property.supports.video.capture: ",  getInfo( System.getProperty("supports.video.capture") ) );
        addInfo("property.mmapi.video.encodings: ",  getInfo( System.getProperty("video.encodings") ) );
        addInfo("property.video.snapshot.encodings: ",  getInfo( System.getProperty("video.snapshot.encodings")) );
        addInfo("property.mmapi.audio.encodings: ",  getInfo(System.getProperty("audio.encodings")) );
        addInfo("property.supports.audio.capture: ",  getInfo(System.getProperty("supports.audio.capture")) );
        addInfo("property.supports.recording: ",  getInfo(System.getProperty("supports.recording")) );
        addInfo("property.supports.mixing: ",  getInfo(System.getProperty("supports.mixing")) );
        
		try {
	        
            Class.forName("javax.microedition.media.Manager");
            addInfo( "MMAPI present:", "yes" );
            addInfo("property.microedition.media.version: ",  getInfo(System.getProperty("microedition.media.version")) );
            addInfo("property.streamable.contents: ",  getInfo(System.getProperty("streamable.contents")) );
            
            String[] supportedProtocols = ProtocolsInfo.getSupportedProtocols(null);
            
            if ( supportedProtocols != null ){
                for ( int i = 0; i < supportedProtocols.length; i++ ){
                	String protocol = supportedProtocols[i];
                    String[] supportedContentTypes = ProtocolsInfo.getSupportedContentTypes( protocol );
                    StringBuffer buffer = new StringBuffer();
                    for ( int j = 0; j < supportedContentTypes.length; j++ ) {
                        buffer.append( supportedContentTypes[j] ).append( '\n' );
                    }
                    addInfo( "Protocol " + protocol + ":", buffer.toString() );
                }
            }
        } catch (Exception e){
            addInfo( "MMAPI present:", "no" );
        }
        
        String nokiaSound = "no";
        try {
            Class.forName("com.nokia.mid.sound.Sound");
            nokiaSound = "yes";
        } catch (Exception e) {
        	// Nokia UI API is not present
        }        
        addInfo( "Nokia Sound: ", nokiaSound );
        
	}
 }
