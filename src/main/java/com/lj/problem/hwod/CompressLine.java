package com.lj.problem.hwod;

/**
 * 为简化处理，多段线的走向只能是水平、竖直、斜向45度。
 * 上图中的多段线可以用下面的坐标串表示:(2,8),(3,7)，(3,6),(3,5),(4,4),(5,3),(6,2),(7,3),(8,4),(7,5)。
 * 但可以发现，这种表示不是最简的，其实只需要存储6个蓝色的关键点即可,它们是线段的起点、拐点、终点,而剩下4个点是冗余的。
 * 现在，请根据输入的包含有冗余数据的多段线坐标列表，输出其最简化的结果。
 * 输入描述:2 8 3 7 3 6 3 5 4 4 5 3 6 2 7 3 8 4 7 5
 *
 * 1、所有数字以空格分隔，每两个数字一组，第一个数字是行号，第二个数字是列号;
 * 2、行号和列号范围为[0,64)，用例输入保证不会越界，考生不必检查;
 * 3、输入数据至少包含两个坐标点,
 *
 * 输出描述:2 8 3 7 3 5 6 2 8 4 7 5
 * 压缩后的最简化坐标列表，和输入数据的格式相同，
 * 补充说明:输出的坐标相对顺序不能变化
 */
public class CompressLine {
    
    public String solve(String input) {
    
        String[] inputArr = input.split(" ");
        
        if (inputArr.length <= 2) {
            return input;
        }
        
        StringBuffer result = new StringBuffer();
        result.append(inputArr[0]).append(" ").append(inputArr[1]).append(" ");
        
        for (int i = 1, k = inputArr.length / 2; i < k;) {
    
            // 斜线1
            if (i < k && is45(inputArr, i, i-1)) {
                i++;
                while (i < k && is45(inputArr, i, i-1)) {
                    i++;
                }
                result.append(inputArr[2*(i-1)]).append(" ").append(inputArr[2*(i-1) + 1]).append(" ");
            }
    
            // 斜线2
            if (i < k && isMinus45(inputArr, i, i-1)) {
                i++;
                while (i < k && isMinus45(inputArr, i, i-1)) {
                    i++;
                }
                result.append(inputArr[2*(i-1)]).append(" ").append(inputArr[2*(i-1) + 1]).append(" ");
            }
            
            // 横线
            if (i < k && isLine(inputArr,i, i-1)) {
                i++;
                while (i < k && isLine(inputArr,i, i-1)) {
                    i++;
                }
                result.append(inputArr[2*(i-1)]).append(" ").append(inputArr[2*(i-1) + 1]).append(" ");
            }
           
            if (i < k && isVertical(inputArr,i, i-1)) {
                i++;
                // 垂直线
                while (i < k && isVertical(inputArr,i, i-1)) {
                    i++;
                }
                result.append(inputArr[2*(i-1)]).append(" ").append(inputArr[2*(i-1) + 1]).append(" ");
            }
        }
        
        return result.toString();
    
    }
    
    private boolean isVertical(String[] inputArr, int curr, int pre) {
        if (inputArr[2*curr + 1].equals(inputArr[2*pre + 1])) {
            return true;
        }
        
        return false;
    }
    
    private boolean isLine(String[] inputArr, int curr, int pre) {
        if (inputArr[2*curr].equals(inputArr[2*pre])) {
            return true;
        }
    
        return false;
    }
    
    private boolean is45(String[] inputArr, int curr, int pre) {
        return (Integer.parseInt(inputArr[2*curr]) - Integer.parseInt(inputArr[2*pre]))
                /(Integer.parseInt(inputArr[2*curr+1]) - Integer.parseInt(inputArr[2*pre+1])) == -1;
    }
    
    private boolean isMinus45(String[] inputArr, int curr, int pre) {
        return (Integer.parseInt(inputArr[2*curr]) - Integer.parseInt(inputArr[2*pre]))
                /(Integer.parseInt(inputArr[2*curr+1]) - Integer.parseInt(inputArr[2*pre+1])) == 1;
    }
    
    public static void main(String[] args) {
        System.out.println(new CompressLine().solve("2 8 3 7 3 6 3 5 4 4 5 3 6 2 7 3 8 4 7 5"));
    }
}
