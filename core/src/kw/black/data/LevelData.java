package kw.black.data;


import com.badlogic.gdx.utils.Array;

public class LevelData {
        private final int[][] data1 = {
                {1,1,1},
                {0,0,1},
                {0,0,1}};
        private final int[][] data2 = {
                {1,1,1},
                {1,1,1}};
        private final int[][] data3 = {
                {1,1},
                {1,1}};
        private final int[][] data4 = {
                {1},
                {1},
        };
        private final int[][] data5 = {
                {1,0,0},
                {1,0,0},
                {1,1,1},
        };
        private final int[][] data6 = {
                {1},
                {1},
                {1}
        };
        private final int[][] data7 = {
                {1},
                {1},
                {1}
        };
        private final int[][] data8 = {
                {1},
                {1},
                {1},
                {1},
                {1}
        };
        private final int[][] data9 = {
                {1,1,1},
        };
        private final int[][] data10 = {
                {1,1},
                {1,1},
                {1,1}
        };
        private final int[][] data11 = {
                {0,0,0},
                {1,1,1},
                {1,1,1},
                {1,1,1},
                {0,0,0}
        };

        private Array<int[][]> shapeData ;
        public LevelData(){
                shapeData = new Array<>();
                shapeData.add(data1);
                shapeData.add(data2);
                shapeData.add(data3);
                shapeData.add(data4);
                shapeData.add(data5);
                shapeData.add(data6);
                shapeData.add(data7);
                shapeData.add(data8);
                shapeData.add(data9);
                shapeData.add(data10);
                shapeData.add(data11);
        }

        public Array<int[][]> shapeData(){
                return shapeData;
        }
}
