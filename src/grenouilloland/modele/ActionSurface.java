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
 * Enumération representant les différents types de surfaces permettant
 * d'appliquer les actions sur la grenouille en fonction de ce type.<br />
 * Il existe différents types de surfaces : l'eau et 5 types de nénuphars.
 * 
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.0
 */
public enum ActionSurface {
    
    /**
     * L'eau tue instantanément la grenouille.
     */
    EAU{
        protected void actionSurGrenouille(final Grenouille grenouille){
            grenouille.modifierNbPointsVie(0);
        }
    },
    /**
     * De couleur verte et n'a aucun effet sur la grenouille. Il ne vieillit 
     * jamais, lui offrant un refuge.
     */
    NENUPHARIMMORTEL{
        @Override
        protected void actionSurGrenouille(final Grenouille grenouille){
            // Rien à faire.
        }
    },
    /**
     * De couleur verte, il n'a aucun effet sur la grenouille.
     */
    NENUPHAR{
        @Override
        protected void actionSurGrenouille(final Grenouille grenouille){
            // Rien à faire.
        }
    },
    /**
     * De couleur jaune, il divise le nombre de point de vie de la grenouille 
     * par 2, et la rend malade. Si la grenouille était déjà malade, elle est
     * tuée instantanément.
     */
    NENUPHARVENENEUX{
        @Override
        protected void actionSurGrenouille(final Grenouille grenouille){
            if(grenouille.lireEtatCourant() == Grenouille.Etat.SAINE){
                grenouille.modifierNbPointsVie(grenouille.lireNbPointsVie()/2);
            }
            else if(grenouille.lireEtatCourant() == Grenouille.Etat.MALADE){
                grenouille.modifierNbPointsVie(0); // On tue
            }
            grenouille.modifierEtatCourant(Grenouille.Etat.MALADE);
        }
    },
    /**
     * De couleur rose, il guérit la grenouille et augmente ses points de vie
     * de 1.
     */
    NENUPHARNUTRITIF{
        @Override
        protected void actionSurGrenouille(final Grenouille grenouille){
            grenouille.modifierNbPointsVie(grenouille.lireNbPointsVie()+1); 
            grenouille.modifierEtatCourant(Grenouille.Etat.SAINE);
        }
    },
    /**
     * De couleur rouge, il guérit la grenouille et double ses points de vie.
     */
    NENUPHARDOPANT{
        @Override
        protected void actionSurGrenouille(final Grenouille grenouille){
            grenouille.modifierNbPointsVie(grenouille.lireNbPointsVie()*2); 
            grenouille.modifierEtatCourant(Grenouille.Etat.SAINE);
        }
    },
    /**
     * De couleur rouge, il tue instantanément la grenouille. Il est 
     * indiscociable du nénuphar dopant.
     */
    NENUPHARMORTEL{
        @Override
        protected void actionSurGrenouille(final Grenouille grenouille){
                grenouille.modifierNbPointsVie(0); // Grenouille morte.
        }    
    };
    
    /**
     * Cette méthode effectue les vérifications d'usage avant le déplacement de
     * la grenouille. Il faut que la grenouille soit vivante et sur une case
     * voisine.
     * 
     * @param modele Le modèle de l'application. Permet de récupérer l'état et 
     * la position de la grenouille
     * @param ligne La ligne où on veut se déplacer
     * @param colonne La colonne où on veut se déplacer
     */
    public void action(final Modele modele, final int ligne, final int colonne){
        // Si la grenouille est morte, il est impossible de la déplacer
        if(modele.lireGrenouille().estMorte()) return;
        
        Iterator<Surface> it = modele.iterator();
        Surface voisine;
        
        // On vérifie que la grenouille peut atteindre la case spécifiée, et on
        // la déplace si c'est le cas.
        while(it.hasNext()){
            voisine = it.next();
            if(voisine != null && voisine.lireLigne() == ligne && voisine.lireColonne() == colonne){
                actionSurGrenouille(modele.lireGrenouille());
                modele.lireGrenouille().modifierLigne(ligne);
                modele.lireGrenouille().modifierColonne(colonne);
                return;
            }
        }
    }    
    
    /**
     * Permet d'effectuer l'action du nénuphar sur la grenouille. L'action
     * effectuée dépend du type de nénuphar.
     * 
     * @param grenouille La grenouille du modèle.
     */
     protected abstract void actionSurGrenouille(Grenouille grenouille);
}
