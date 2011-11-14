/**
 * 
 */
package org.cam.card;

import java.util.ArrayList;
import java.util.List;

import org.cam.paigow.*;

/**
 * @author ALX
 *
 */
public class HandResultConverter {

	public static List<HandResult> convertPaiGao2Hand(List<PaiGowHandResult> res) {
    List<HandResult> ret = new ArrayList<HandResult>();
    for (PaiGowHandResult pRes : res) {
      ret.add(pRes.getResult());
    }
    return ret;
	}
}
