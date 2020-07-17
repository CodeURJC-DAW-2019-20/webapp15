import {Team} from './team';

export interface Match {
    localTeam: Team;
    visitantTeam: Team;
    dateMatch: string;
    result: string;
    betSelected: string;
    betLocal: string;
    betTied: string;
}
