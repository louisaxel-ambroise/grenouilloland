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

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Color;

/**
 * Le modèle graphique permet de représenter graphiquement les
 * données contenues par le modèle
 * 
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.0
 */
public class ModeleGraphique extends JPanel {
    
    /**
     * Initialise le modèle graphique.
     * 
     * @param vue La vue contennant ce modèle graphique
     */
    public ModeleGraphique(final Vue vue){
        this.vue = vue;
        
        int resolution = vue.lirePresentateur().lireModele().lireResolution();
        mare = new SurfaceGraphique[resolution][resolution];
        
        // On applique la couleur de fond
        setBackground(couleurEau);
        
        // On ajout le bon gestionnaire de positionnement
        setLayout(new GridLayout(resolution, resolution));
        
        // Initialisation des SurfaceGraphique
        for(int i = 0 ; i < resolution ; i++){
            for(int j = 0 ; j < resolution ; j++){
                mare[i][j] = new SurfaceGraphique(i, j, this);
                add(mare[i][j]); // On ajoute la surface au layout
            }
        }
    }
    
    /**
     * @return La vue contenant ce modèle graphique
     */
    public Vue lireVue(){
        return vue;
    }
    
    /**
     * Met à jour l'affichage du modèle (met à jour chacune de ses 
     * SurfaceGraphique).
     */
    public void mettreAJour(){
        for(int ligne = 0 ; ligne < mare.length ; ligne++){
            for(int colonne = 0 ; colonne < mare.length ; colonne++){
                mare[ligne][colonne].mettreAJour();
            }
        }
    }
    
    /** La vue contenant le modèle graphique. */
    protected Vue vue;
    
    /** 
     * Le tableau des surfaces graphiques représentant la mare du
     * modèle
     */
    protected SurfaceGraphique[][] mare;
    
    /** La couleur de l'eau. */
    protected static final Color couleurEau = new Color(98, 165, 225);
    
}
