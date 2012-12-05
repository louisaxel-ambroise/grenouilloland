/*   Grenouilloland is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package grenouilloland.vue;

import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import javax.swing.JScrollBar;

/**
 * Cette classe permet de représenter la barre de sélection, permettant
 * de choisir la résolution de la mare (compris entre la résolution
 * minimale et la résolution maximale du présentateur).
 *
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.0
 */
class Resolution extends JScrollBar implements AdjustmentListener {

    /**
     * Instancie la barre de résolution.
     * 
     * @param orientation L'orientation de la barre de résolution
     * @param vue La vue propriétaire de la résolution
     */
    public Resolution(final int orientation, final Vue vue){
        super(orientation, 
              vue.lirePresentateur().lireModele().lireResolution(),
              1,
              vue.lirePresentateur().lireResolutionMin(), 
              vue.lirePresentateur().lireResolutionMax()+1);
        this.vue = vue;
        
        addAdjustmentListener(this);
    }
    
    /**
     * @return La vue possédant la barre de résolution
     */
    public Vue lireVue(){
        return vue;
    }
    
    /**
     * Cette méthode est appelée à chaque changement de la valeur de la
     * barre de défilement.<br />
     * On demande alors au présentateur de créer un nouveau modèle avec 
     * une mare de la taille correspodant à cette valeur.
     * 
     * @param e L'action résultat du changement de valeur de la barre.
     */
    @Override
    public void adjustmentValueChanged(final AdjustmentEvent e){
        lireVue().cbNouveauModele(e.getValue());
    }
    
    /** La vue propriétaire de la résolution */
    protected Vue vue;
}

