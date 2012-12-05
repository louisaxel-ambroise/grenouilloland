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
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import grenouilloland.modele.Modele;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;

/**
 * Le panneau de contrôle permet d'afficher le nombre de points de vie
 * de la grenouille.
 * 
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.0
 */
class PanneauControle extends JPanel{
    
    /**
     * Constructeur logique du panneau de contrôle.
     * 
     * @param vue La vue principale de l'application
     */
    public PanneauControle(final Vue vue){
        super(new BorderLayout());
        this.vue = vue;
        setBorder(BorderFactory.createTitledBorder(titreBordure));
        
        texte = new JLabel("1", icone, JLabel.HORIZONTAL);
        add(texte, BorderLayout.CENTER);
    }
    
    /**
     * Met à jour le composant (affiche le nombre de points de vie de 
     * la grenouille)
     */
     protected void mettreAJour(){
         Modele m = vue.lirePresentateur().lireModele();
         texte.setText(m.lireGrenouille().lireNbPointsVie()+"");
     }
    
    /** Le titre du panneau de contrôle */
    protected final static String titreBordure = "Points de vie :";
    
    /** Le chemin vers l'image de coeur */
    protected JLabel texte;
    
    /** Le chemin vers l'icône du JLabel */
    protected static final String cheminIcone = 
    "ressources/images/jeu/coeur.png";
    
    /** L'icône de coeur à afficher dans le JLabel */
    protected static final ImageIcon icone;
    static{
        ClassLoader loader = PanneauControle.class.getClassLoader();
        icone = new ImageIcon(loader.getResource(cheminIcone));
    }
    
    /** La vue possédant ce panneau de contrôle */
    protected final Vue vue;

    
}

