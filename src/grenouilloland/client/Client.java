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
package grenouilloland.client;

import grenouilloland.presentateur.Presentateur;

/**
 * Programme principale de l'application <br />
 * Permet de lancer le jeu Grenouilloland.
 * 
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.0
 */
public class Client{
    
        /**
         * Point d'entree de la Java Virtual Machine.
         *
         * @param args les arguments de la ligne de commandes.
         */
        public static void main(String args[]) {
            
            // Crée un présentateur et le démarre
            Presentateur p = new Presentateur(5, 12, 10);
            p.demarrer();
        }
}
