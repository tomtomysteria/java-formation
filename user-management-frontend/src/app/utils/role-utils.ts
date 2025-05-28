import { Role } from '../models/user.model';

/**
 * Convertit une liste de rôles en une chaîne de codes lisibles.
 * @param roles Liste des rôles à convertir.
 * @returns Une chaîne contenant les codes des rôles séparés par des virgules.
 */
export function getRolesCodes(roles: Role[]): string {
  return roles.map(role => role.name).join(', ');
}

/**
 * Convertit une liste de rôles en une chaîne de labels lisibles.
 * @param roles Liste des rôles à convertir.
 * @returns Une chaîne contenant les labels des rôles séparés par des virgules.
 */
export function getRolesLabels(roles: Role[]): string {
  return roles.map(role => mapRole(role.name)).join(', ');
}

/**
 * Mappe un code de rôle à un label lisible.
 * @param role Le code du rôle à mapper.
 * @returns Le label correspondant au rôle.
 */
function mapRole(role: string): string {
  switch (role) {
    case 'ADMIN': return 'Administrateur';
    case 'USER': return 'Utilisateur';
    default: return role;
  }
}
