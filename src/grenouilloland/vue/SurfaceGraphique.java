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

import java.awt.Graphics;

import grenouilloland.modele.ActionSurface;
import grenouilloland.modele.Surface.Taille;
import grenouilloland.modele.Surface;
import grenouilloland.modele.Grenouille;
import grenouilloland.modele.Modele;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.net.URL;
import java.util.HashMap;

/**
 * Cette classe représente une {@link grenouilloland.modele.Surface} 
 * graphiquement. Une surface est un bouton. Un clic sur une surface
 * essaye de déplacer la grenouille sur la case correspondante.
 *
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.1
 */
public class SurfaceGraphique extends JButton implements ActionListener{
    
    /**
     * Instancie une surface graphique.
     * 
     * @param ligne La ligne où est située la surface
     * @param colonne La colonne où est située la surface
     * @param modele Le modèle graphique contenant cette surface
     */
    public SurfaceGraphique(final int ligne, final int colonne, 
                               final ModeleGraphique modele){
        this.ligne   = ligne;
        this.colonne = colonne;
        this.modele  = modele;
        
        setPreferredSize(new Dimension(75, 50));
        setContentAreaFilled(false);
        addActionListener(this);
    }
    
    /**
     * Méthode appelée lors du clic souris sur la surface graphique.
     */
    @Override
    public void actionPerformed(final ActionEvent e){
        modele.lireVue().cbDeplacerGrenouille(this);
    }
    
    /**
     * @return Le modèle graphique contenant cette surface
     */
    public ModeleGraphique lireModeleGraphique(){
        return modele;
    }
    
    /**
     * @return La ligne de la surface
     */
    public int lireLigne(){
        return ligne;
    }
    
    /**
     * @return La colonne de la surface
     */
    protected int lireColonne(){
        return colonne;
    }
    
    /**
     * Met à jour l'affichage de la surface graphique en fonction de 
     * la surface du modèle correspondant
     */
    protected void mettreAJour(){
        Modele m = modele.lireVue().lirePresentateur().lireModele();
        Surface surface = m.lireSurface(ligne, colonne);
        ActionSurface action = surface.lireTypeSurface();
        
        int ligneGr = m.lireGrenouille().lireLigne();
        int colonneGr = m.lireGrenouille().lireColonne();
        int etat = 0;
        if(ligneGr == ligne && colonneGr == colonne)
            etat = m.lireGrenouille().lireEtatCourant().ordinal()+1;
        
        if(couleurSurface.get(action) >= 0){
            int taille = surface.lireTaille().ordinal();
            setIcon(nenuphars[couleurSurface.get(action)][taille][etat]);
        }
        else
            setIcon(null);
    }

    /** La ligne où est située la surface dans la mare */
    protected final int ligne;
    /** La colonne où est située la surface dans la mare */
    protected final int colonne;
    /** Le modèle graphique contenant cette surface graphique */
    protected final ModeleGraphique modele;
    
    /** Le dossier contenant toutes les images */
    protected static final String dossierImages = 
    "ressources/images/jeu";
    /** Les différentes couleurs que peuvent prendre les nénuphars */
    protected static final String[] couleurs = {"rouge", "vert", 
                                                "jaune", "rose"};
    
    /** Les différentes tailles que peuvent prendre les nénuphars */
    protected static final String[] tailles = {"grand", "moyen", 
                                               "petit"};
    
    /** Les états que peut avoir la grenouille sur chaque nénuphar */
    protected static final String[] etat = {"sans", "normale", 
                                            "malade"};
    
    /** Tableau contenant toutes les images des nénuphars */
    protected static final ImageIcon[][][] nenuphars;
    static{
        // Crée le tableau d'images à 3 dimensions et charge chacune 
        //    des images dans ce tableau.
        nenuphars = 
        new ImageIcon[couleurs.length][tailles.length][etat.length];
        ClassLoader loader = ActionQuitter.class.getClassLoader();
        
        /* Pour chaque couleur et chaque taille de nénuphar, ainsi que
         * pour chaque état de la grenouille, on charge l'image.
         * Les noms d'image sont de la forme :
         * couleur-taille-etat.png */
        for(int i=0 ; i<couleurs.length ; i++){
             for(int j=0 ; j<tailles.length ; j++){
                 for(int k=0 ; k<etat.length ; k++){
                     String chemin = dossierImages+"/"+couleurs[i]+"-"+
                                     tailles[j]+"-"+etat[k]+".png";
                     URL urlImage = loader.getResource(chemin);
                     nenuphars[i][j][k] = new ImageIcon(urlImage);
                 }
             }
        }
    }
    
    /** 
     * Hashmap contenant les équivalences ActionSurface-image.
     * Elle permet d'associer à chaque type de nénuphar la couleur qui
     * lui est associée
     */
    protected static final HashMap<ActionSurface, Integer> couleurSurface;
    static{
        couleurSurface = new HashMap<ActionSurface, Integer>();
        couleurSurface.put(ActionSurface.EAU, -1);
        couleurSurface.put(ActionSurface.NENUPHARIMMORTEL, 1);
        couleurSurface.put(ActionSurface.NENUPHAR, 1);
        couleurSurface.put(ActionSurface.NENUPHARDOPANT, 0);
        couleurSurface.put(ActionSurface.NENUPHARMORTEL, 0);
        couleurSurface.put(ActionSurface.NENUPHARNUTRITIF, 3);
        couleurSurface.put(ActionSurface.NENUPHARVENENEUX, 2);
    }
}
