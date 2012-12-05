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

import javax.swing.JProgressBar;

/**
 * Cette classe permet d'afficher graphiquement le temps restant avant 
 * la fin du chronomètre de la partie (et donc la fin du jeu).
 *
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.0
 */
class Chronometre extends JProgressBar {
    
    /**
     * Constructeur logique du chronomètre.
     * 
     * @param vue La vue ayant instancié ce chronomètre
     */
    public Chronometre(final Vue vue){
        super(JProgressBar.HORIZONTAL, 0, 59);
        this.vue = vue;
        mettreAJour();
    }
    
    /**
     * Met à jour le chronomètre à partir du modèle.
     */
    protected void mettreAJour(){
        setValue(vue.lirePresentateur().lireModele().lireTempsRestant());
    }
    
    /** La vue contenant ce chronomètre */
    protected final Vue vue;
}

