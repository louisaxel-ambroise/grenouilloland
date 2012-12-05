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
package grenouilloland.modele;

import java.lang.Iterable;
import java.util.Iterator;

/**
 * Cette classe représente le modèle de l'application. Il contient une 
 * grenouille et une mare, permettant de jouer à Grenouilloland.<br/>
 * Le but du jeu est d'arriver à amener la grenouille au coin opposé de
 * sa position de départ. Elle doit évoluer sur différentes surfaces, 
 * qui ont un impact sur sa vie.
 * 
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.1
 */
public class Modele implements Iterable<Surface>{    
    
    /**
     * Instancie le modele.
     * 
     * @param resolution La taille de la mare à créer
     */
    public Modele(final int resolution){
        mare = new Surface[resolution][resolution];
        
        // Initialisation des surfaces
        for (int i = 0; i < resolution; i++){
            for (int j = 0; j < resolution; j++){
                if((i==resolution-1 && j==0)  || (i==0 && j==resolution-1)){
                    mare[i][j] = new Surface(i,j, ActionSurface.NENUPHARIMMORTEL);
                }
                else{
                    mare[i][j] = new Surface(i,j, ActionSurface.EAU);
                }
            }
        }
        grenouille = new Grenouille(resolution-1, 0, 1, 
                                    Grenouille.Etat.SAINE);
        tempsRestant = 60; // On a une minute pour jouer
    }
    
    /**
     * Deplace la grenouille vers la case spécifiée
     * 
     * @param ligne La ligne de la nouvelle position
     * @param colonne La colonne de la nouvelle position
     */
    public void deplacerGrenouille(final int ligne, final int colonne){
            mare[ligne][colonne].lireTypeSurface().action(this, ligne, colonne);
            //creerChemin(grenouille.lireLigne(), grenouille.lireColonne());
    }
    
    /**
     * Réinitialise le modèle, i.e. remet la grenouille à la position de
     * départ et supprime tous les nénuphars
     */
    public void reinitialiser(){
        for (int i = 0; i < mare.length; i++){
            for (int j = 0; j < mare.length; j++){
                if((i==mare.length-1 && j==0)  || (i==0 && j==mare.length-1)){
                    mare[i][j] = new Surface(i,j, ActionSurface.NENUPHARIMMORTEL);
                }
                else{
                    mare[i][j] = new Surface(i,j, ActionSurface.EAU);
                }
            }
        }
        tempsRestant = 60;
        grenouille = new Grenouille(mare.length-1, 0, 1, Grenouille.Etat.SAINE);
    }
    
    /**
     * Détermine le type de surface présent à la position spécifiée.
     * 
     * @return Le type de surface à la position passée en paramètre
     */
    public ActionSurface lireTypeSurface(final int ligne, final int colonne){
        return mare[ligne][colonne].lireTypeSurface();
    }
    
    /**
     * Permet de créer un chemin entre la grenouille et la case d'arrivée.
     *
     * @param ligne Représente la ligne de la grenouille.
     * @param colonne Représente la colonne de la grenouille.
     */
     public void creerChemin(final int ligne, final int colonne){
         java.util.Random rand = new java.util.Random( ) ; 

        // Créer les lignes pour aller à la case d'arrivée.
        for (int i = ligne; i > 0; i--){
            if(mare[i][lireResolution()-1].lireTypeSurface() == ActionSurface.EAU){
                mare[i][lireResolution()-1].modifierTypeSurface(ActionSurface.values()[rand.nextInt(5)+2]);
            }
            if(mare[i][colonne].lireTypeSurface() == ActionSurface.EAU){
                mare[i][colonne].modifierTypeSurface(ActionSurface.values()[rand.nextInt(5)+2]);
            }
        }
        
        // Créer les colonnes pour aller à la case d'arrivée.
        for(int j = colonne; j < lireResolution(); j++){ 
            if(mare[ligne][j].lireTypeSurface() == ActionSurface.EAU){
                mare[ligne][j].modifierTypeSurface(ActionSurface.values()[rand.nextInt(5)+2]);
            }
            if(mare[0][j].lireTypeSurface() == ActionSurface.EAU){
                mare[0][j].modifierTypeSurface(ActionSurface.values()[rand.nextInt(5)+2]);
            }
        }
     }
     
    /**
     * Retourne la surface présente dans la mare à la position spécifiée.
     * 
     * @param ligne La ligne où se trouve la surface
     * @param colonne La colonne où se trouve la surface
     * @return La surface à la position spécifiée
     */
    public Surface lireSurface(final int ligne, final int colonne){
         return mare[ligne][colonne];
    }
    
    /**
     * Génère l'étape suivante de la mare. Fait vieillir chaque nénuphar, et
     * crée un chemin entre la position de la grenouille et la position
     * d'arrivée.
     */
    public void genererEtapeSuivante(){
        for (int i = 0; i < mare.length; i++) {
            for (int j = 0; j < mare.length; j++) {
                mare[i][j].vieillir();
            }            
        }
        if(mare[grenouille.lireLigne()][grenouille.lireColonne()].lireTypeSurface() == ActionSurface.EAU){
            grenouille.modifierNbPointsVie(0);
        }
        else{
            creerChemin(grenouille.lireLigne(), grenouille.lireColonne());
        }
        // Une seconde vient de passer.
        tempsRestant--;
    }
    
    /**
     * @return La résolution de la mare
     */
    public int lireResolution(){
        return mare.length;
    }
    
    /**
     * @return Le temps restant
     */
    public int lireTempsRestant(){
        return tempsRestant;
    }
    
    /**
     * La partie est terminée si une des deux conditions suivantes est
     * vérifiée :<br />
     * La grenouille n'a plus de points de vie OU la grenouille est 
     * arrivée à la case attendue.
     * 
     * @return Vrai si la partie est finie, faux sinon.
     */
     public boolean estTerminee(){
        if(tempsRestant == 0) return true;
        if(grenouille.lireNbPointsVie() <= 0) return true;
        if(grenouille.lireLigne() == 0 && grenouille.lireColonne() == mare.length-1)
            return true;
        
        return false;
     }
    
    
    /**
     * Détermine si le joueur a gagné la partie. La partie est gagnée
     * si la grenouille est arrivée à la bonne case.
     * 
     * @return True si le joueur a gagné, False sinon.
     */
     public boolean estGagnant(){
        if(grenouille.lireLigne() == 0 && grenouille.lireColonne() == mare.length-1)
            return true;
        
        return false;
     }
     
    /**
     * @return La grenouille présente dans le modèle
     */
    public Grenouille lireGrenouille(){
         return grenouille;
    }
    
    /**
     * Retourne l'itérateur à partir de la position de la grenouille. 
     * Cet itérateur permet de connaître les positions atteignables par 
     * la grenouille.
     * 
     * @return l'itérateur sur la mare à partir de la position de la grenouille
     */
    @Override
    public Iterator<Surface> iterator(){
        return new Iterateur(grenouille.lireLigne(),
                             grenouille.lireColonne(),
                             mare);
    }
    
    /** 
     * Représente la mare sur laquelle évolue la grenouille.
     * Une mare est une grille de Surface, comportant elle-même des
     * ActionSurface.
     */
    protected final Surface[][] mare;
    
    /** La grenouille qui va se déplacer dans la mare. */
    protected Grenouille grenouille;
    
    /** Le temps restant (en secondes) avant la fin de la partie */
    protected int tempsRestant;
}
