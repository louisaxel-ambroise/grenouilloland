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
 * Classe representant une grenouille. Une grenouille est caractérisée
 * par un état (saine ou malade), un nombre de points de vie et une 
 * position (ligne/colonne) sur la mare.
 * 
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.0
 */
public class Grenouille{
    
        
    /**
     * Constructeur logique, initialise la grenouille avec des points de vie.
     * 
     * @param ligne La ligne où est située la grenouille
     * @param colonne La colonne où est située la grenouille
     * @param nbPointsVie Points de vie que possède la grenouille.
     * @param etatCourant L'état de la grenouille (malade ou saine).
     */
    public Grenouille(final int ligne, final int colonne,
                      final int nbPointsVie, final Etat etatCourant){
        this.nbPointsVie = nbPointsVie;
        this.etatCourant = etatCourant;
        this.ligne = ligne;
        this.colonne = colonne;
    }
    
    /**
     * Indique si la grenouille est morte (point de vie égale à 0).
     * 
     * @return True si la grenouille est morte.
     */
    public boolean estMorte(){
        return (nbPointsVie == 0);
    }
    
    /**
     * @return Nombre de points de vie que possède la grenouille.
     */
    public int lireNbPointsVie(){
        return nbPointsVie;
    }
    
    /**
     * @return L'état courant de la grenouille.
     */
    public Etat lireEtatCourant(){
        return etatCourant;
    }
    
    /**
     * Permet de modifier le nouvel état de la grenouille.
     * 
     * @param etatCourant Indique le nouvel état de la grenouille.
     */
    public void modifierEtatCourant(final Etat etatCourant){
        this.etatCourant = etatCourant;
    }
    
    
    /**
     * Permet de modifier les points de vie que possède la grenouille.
     * 
     * @param nbPointsVie Indique les nouveaux points de vie de la grenouille.
     */
    public void modifierNbPointsVie(final int nbPointsVie){
        this.nbPointsVie = nbPointsVie;
    }
    
    /**
     * @return Position de la grenouille en ligne
     */
    public int lireLigne(){
        return ligne;
    }
    
    /**
     * @return Position de la grenouille en colonne.
     */
    public int lireColonne(){
        return colonne;
    }
    
    /**
     * Modifie la position de la grenouille en ligne.
     * 
     * @param ligne Nouvelle position.
     */
    public void modifierLigne(final int ligne){
        this.ligne = ligne;
    }
    
    /**
     * Modifie la position de la grenouille en colonne.
     * 
     * @param colonne Nouvelle position.
     */
    public void modifierColonne(final int colonne){
        this.colonne = colonne;
    }
    
    /** Représente l'état de la grenouille. */
    public enum Etat {
        /**
         * La grenouille saine peut tomber malade sans mourir.
         */
        SAINE, 
        /**
         * Une grenouille malade ne peut tomber à nouveau sur un 
         * nénuphar la contaminant, sous peine de mourir instantanément.
         */
        MALADE;
    };
    
    /** Nombre de points de vie que possède la grenouille. */
    protected int nbPointsVie;
    
    /** Etat actuel de la grenouille. */
    protected Etat etatCourant;
    
    /** Ligne sur laquelle se situe la grenouille dans la mare. */
    protected int ligne;
    
    /** Colonne sur laquelle se situe la grenouille dans la mare. */
    protected int colonne;
    
}
