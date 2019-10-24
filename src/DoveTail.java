public class DoveTail {
    private int replaceCost = 0;
    private int insertionCost = 0;
    private int matchingCost = 0;

    public DoveTail(int replaceCost, int insertionCost, int matchingCost){
        this.replaceCost = replaceCost;
        this.insertionCost = insertionCost;
        this.matchingCost = matchingCost;
    }

    public int compute(String seq1, String seq2){
        int[][] scores = matrixConstruction(seq1,seq2);
        return getScore(scores,seq1.length(),seq2.length());
    }

    private int getScore(int[][] scoreMatrix, int lenSeq1, int lenSeq2){
        int max  = Integer.MIN_VALUE;
        try {
            for (int i = 1; i < scoreMatrix[1].length; i++) {
                max = Math.max(scoreMatrix[i][scoreMatrix[1].length - 1], max);
            }
            for (int i = 1; i < scoreMatrix[0].length; i++) {
                max = Math.max(scoreMatrix[scoreMatrix[0].length - 1][i], max);
            }
            return max;
        }
        catch (Exception ex){
            return 0;
        }
    }

    private int[][] matrixConstruction(String seq1, String seq2){
        char s[] = seq1.toCharArray();
        char t[] = seq2.toCharArray();
        int val,i,j;
        int[][] scores = new int[s.length+1][t.length+1];
        for (i = 0; i < t.length+1; i++) {
            scores[0][i] = 0;
        }
        for (i = 1; i < s.length+1; i++) {
            scores[i][0] = 0;
        }
        for (i = 1; i < s.length+1; i++) {
            for (j = 1; j < t.length+1; j++) {
                if(seq1.charAt(i-1)==seq2.charAt(j-1))
                    val = matchingCost;
                else
                    val = replaceCost;
                scores[i][j] = Math.max(scores[i][j-1] + insertionCost,
                                            Math.max(scores[i-1][j] + insertionCost,
                                                    scores[i-1][j-1] + val));
            }
        }
        return scores;
    }
}
