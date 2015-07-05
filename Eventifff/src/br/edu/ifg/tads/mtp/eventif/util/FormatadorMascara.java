package br.edu.ifg.tads.mtp.eventif.util;

import javax.swing.text.MaskFormatter;

public class FormatadorMascara {
	public MaskFormatter mascara(String Mascara) {
	    MaskFormatter stringMascara = new MaskFormatter();
	    try {
	           stringMascara.setMask(Mascara);
	           stringMascara.setPlaceholderCharacter(' ');
	    } catch (Exception e) {
	            e.printStackTrace();
	    }
	    return stringMascara;
	}
	
	
}
