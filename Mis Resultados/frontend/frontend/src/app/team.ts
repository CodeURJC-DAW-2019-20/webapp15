export interface Team {
    id: number;
    name: string;
    winners: number;
    lossers: number;
    tied: number;
    points: number;
    goalsInFavor: number;
    goalsAgainst: number;
    position: number;
    league: string;
    direction: string;
    matches: string[],
    nameImgTemplate: string;
    nameImgShield: string;
}
