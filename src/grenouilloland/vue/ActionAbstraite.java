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

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.KeyStroke;

/**
 * Cette classe permet de représenter une action abstraite. Elles sont
 * appelées lors d'actions sur les widgets graphiques.
 *
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.0
 */
abstract class ActionAbstraite extends AbstractAction{
    
    /**
     * Instancie l'action abstraite.
     *
     * @param nom Le nom de l'action
     * @param icone L'icône représentant l'action
     * @param bulleAide Le texte présent dans l'infobulle de l'action
     * @param mnemonique Lettre permettant de désigner l'action
     * @param accelerateur Le raccourci clavier de l'action
     * @param vue La vue ayant instancié cette actions
     */
    public ActionAbstraite(final String nom, final Icon icone, 
                          final String bulleAide, final int mnemonique,
                          final KeyStroke accelerateur, final Vue vue){
        super(nom, icone);
        putValue(Action.SHORT_DESCRIPTION, bulleAide);
        putValue(Action.MNEMONIC_KEY, mnemonique);
        putValue(Action.ACCELERATOR_KEY, accelerateur);
        this.vue = vue;
    }
    
    /**
     * @return La vue contenant l'action abstraite
     */
    public Vue lireVue(){
        return vue;
    }
    
    /** La vue contenant l'objet pouvant appeler l'action abstraite */
    protected Vue vue;
}
