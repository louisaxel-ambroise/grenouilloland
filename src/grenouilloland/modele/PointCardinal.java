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
 * Une énumération qui représente tout les points cardinaux, 
 * qui permettent d'aller tout autour d'une surface.
 * 
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.0
 */
enum PointCardinal{
    
    /** Au-dessus de la position actuelle */
    NORD(0,-1),
    /** À droite de la position actuelle */
    EST(1,0),
    /** En bas de la position actuelle */
    SUD(0,1),
    /** À gauche de la position actuelle */
    OUEST(-1,0);
    
    
    /**
     * @return nombre de déplacements en x.
     */
    public int lireDx(){
        return dx;
    }
    
    /**
     * @return nombre de déplacements en y.
     */
    public int lireDy(){
        return dy;
    }
    
    /**
     * Constructeur, qui permet d'indiquer de combien de x et de y il 
     * faut se déplacer pour atteindre la position.
     * 
     * @param dx Déplacement en x pour atteindre la position souhaitée
     * @param dy Déplacement en y pour atteindre la position souhaitée 
     */
    private PointCardinal(final int dx, final int dy){
        this.dx = dx;
        this.dy = dy;
    }
    
    /** Déplacement en x pour atteindre la position. */
    protected int dx;
    
    /** Déplacement en y pour atteindre la position. */
    protected int dy;
}
