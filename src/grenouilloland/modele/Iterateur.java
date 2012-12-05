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

import java.util.Iterator;

/**
 * Classe representant un iterateur de surface indiquant toutes les 
 * surfaces qui se situent autour d'une surface choisie.
 * 
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.0
 */
class Iterateur implements Iterator<Surface> {
    
    /**
     * Constructeur qui initialise un itérateur, permettant de parcourir les surfaces voisines.
     * 
     * @param ligne Représente la ligne de la surface source.
     * @param colonne Représente la colonne de la surface source.
     * @param mare La grille de surface du modèle.
     */
    public Iterateur(final int ligne, final int colonne, final Surface[][] mare){
        this.ligne = ligne;
        this.colonne = colonne;
        this.mare = mare;
    }
    
    /**
     * Cette méthode permet de renvoyer la surface suivante (voisine).
     * 
     * @return La surface suivante dans l'itération
     */
     @Override
     public Surface next(){
         int x = ligne + PointCardinal.values()[position].lireDx();
         int y = colonne + PointCardinal.values()[position].lireDy();
         position++;
         if(x>=0 && x<mare.length && y>=0 && y<mare.length)
            return mare[x][y];
        else
            return null;
     }
     
     /**
      * Indique si il reste encore une voisine ou non.
      * 
      * @return True si il reste des itérations.
      */
     @Override
     public boolean hasNext(){
        return (position < PointCardinal.values().length);
     }
     
     /**
      * Retire un élément de l'itération.
      * Méthode non implémentée ici.
      */
     @Override
     public void remove(){
        
     }
     
     /**
      * @return La ligne du point d'origine.
      */
     public int lireLigne(){
         return ligne;
     }
     
     /**
      * @return La colonne du point d'origine.
      */
     public int lireColonne(){
         return colonne;
     }
     
     /**
      * @return La mare qui contient toutes les surfaces
      */
     public Surface[][] lireMare(){
         return mare;
     }
     
     /** Ligne du point d'origine. */
    protected int ligne;
    
    /** Colonne du point d'origine. */
    protected int colonne;
    
    /** Mare contient toutes les surfaces */
    protected Surface[][] mare;
    
    /** 
     * Indique dans quel position nous sommes, sa valeur représente 
     * l'ordinal dans le type énuméré PointCardinal. 
     */
    protected int position = 0;
    
}
