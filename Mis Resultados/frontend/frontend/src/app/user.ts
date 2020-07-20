export interface User {
    id: number;
    name: string;
    surname?: string;
    email: string;
    passwordHash: string;
    fav_team?: string; 
    acc_balance?: number;
    roles?: string[];
    authData?: string;
    
}
