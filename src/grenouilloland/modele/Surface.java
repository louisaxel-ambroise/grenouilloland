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

/**
 * Cette classe représente une case de la grille.
 * 
 * @author Louis-Axel AMBROISE et Thibault LEFRANCOIS
 * @version 1.0
 */
public class Surface {
     
    /**
     * Constructeur logique, initialise une surface.
     * 
     * @param ligne Représente la ligne sur laquelle se trouve la surface.
     * @param colonne La colonne sur laquelle se trouve la surface.
     * @param typeSurface Le type de surface.
     */
    public Surface(final int ligne, final int colonne, final ActionSurface typeSurface){
        this.ligne = ligne;
        this.colonne = colonne;
        this.typeSurface = typeSurface;
        tailleCourante = Taille.GROS;
    }
    
    /**
     * @return La ligne de la surface.
     */
    public int lireLigne(){
        return ligne;
    }
    
    /**
     * @return La colonne de la surface.
     */
    public int lireColonne(){
        return colonne;
    }
    
    /**
     * @return Le type de surface.
     */
    public ActionSurface lireTypeSurface(){
        return typeSurface;
    }
    
    /**
     * @return Taille de la surface.
     */
    public Surface.Taille lireTaille(){
        return tailleCourante;
    }
    
    /**
     * Attribue une nouvelle taille à la surface.
     * 
     * @param tailleCourante Nouvelle taille pour la surface.
     */
     public void modifierTaille(final Taille tailleCourante){
         this.tailleCourante = tailleCourante;
     }
     
    /**
     * Permet de modifier le type de surface contenu sur cette case.
     * A chaque modification du type de surface, on remet la taille de
     * celle-ci à la taille maximale.
     * 
     * @param typeSurface Le nouveau de type pour la surface
     */
     public void modifierTypeSurface(final ActionSurface typeSurface){
           this.typeSurface = typeSurface;
           tailleCourante = Taille.GROS;
     }
    
    /**
     * Permet d'effectuer l'action de vieillir un nénuphar. Tous les 
     * nénuphars vieillissent (à part les immortels), passant par les 
     * tailles GROS, MOYEN et PETIT. Une fois passée l'étape petit, ils
     * redeviennent de l'eau.
     */
     public void vieillir(){
        if(typeSurface == ActionSurface.EAU ) return;
        if(typeSurface == ActionSurface.NENUPHARIMMORTEL) return;
        
        if(tailleCourante.ordinal() < Taille.values().length-1){
            modifierTaille(Taille.values()[tailleCourante.ordinal()+1]);
		}
        else{
            modifierTypeSurface(ActionSurface.EAU);
		}
    }
      
    /** 
     * Cette énumération représente les différentes tailles possible 
     * pour une surface. Les nénuphars (à l'exception des immortels) 
     * passent respectivement par ces 3 états à chaque coup d'horloge.
     */
    public enum Taille{
        /**
         * La première étape d'un nénuphar est la taille GROS. Il 
         * deviendra ensuite MOYEN et PETIT (sauf pour les immortels)
         */
        GROS, 
        /**
         * Un nénuphar MOYEN a encore 2 tours d'horloge à vivre. C'est
         * l'étape intermédiaire
         */
        MOYEN, 
        /**
         * Un PETIT nénuphar est à la fin de sa vie. À l'étape d'après,
         * il redeviendra de l'eau.
         */
        PETIT;
    };

    /** Ligne sur laquelle est positionnée la surface dans la mare */
    protected int ligne;
    
    /** Colonne sur laquelle est positionnée la surface dans la mare */
    protected int colonne;
    
    /** Le type de surface actuel que contient cette case */
    protected ActionSurface typeSurface;
    
    /** 
     * La taille actuelle de la surface. 
     */
    protected Taille tailleCourante;
}
