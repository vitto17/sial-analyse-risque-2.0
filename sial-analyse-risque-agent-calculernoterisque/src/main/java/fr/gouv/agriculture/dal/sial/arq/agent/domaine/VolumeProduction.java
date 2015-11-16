package fr.gouv.agriculture.dal.sial.arq.agent.domaine;

import java.math.BigDecimal;

/**
 *
 * @author frederic.danna
 */
public class VolumeProduction {

    private String uniteRfa;
    private BigDecimal volume;

    /**
     * @return the uniteRfa
     */
    public String getUniteRfa() {
        return uniteRfa;
    }

    /**
     * @param uniteRfa the uniteRfa to set
     */
    public void setUniteRfa(String uniteRfa) {
        this.uniteRfa = uniteRfa;
    }

    /**
     * @return the volume
     */
    public BigDecimal getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

}
