class Solution {
    // Time Complexity: O(N^2+NlogN) => O(N^2)  because there is substring operation
    // Space Complexity: O(N) for storing all unique elements
    public String countOfAtoms(String formula) {
        TreeMap<String, Integer> map = new TreeMap<>();
        Stack<TreeMap<String, Integer>> stack = new Stack<>();
        int i = 0;
        int len = formula.length();
        while (i < len) {
            char ch = formula.charAt(i);
            if (ch == '(') {
                stack.push(map);
                map = new TreeMap<>();
                i++;
            } else if (ch == ')') {
                i++;
                int val = 0;
                while (i < len && Character.isDigit(formula.charAt(i))) {
                    val = val * 10 + formula.charAt(i) - '0';
                    i++;
                }
                int count = val == 0 ? 1 : val;
                TreeMap<String, Integer> temp = map;
                map = stack.pop();
                for (String name : temp.keySet()) {
                    map.put(name, map.getOrDefault(name, 0) + temp.get(name) * count);
                }
            } else {
                int j = i + 1;
                while (j < len && Character.isLowerCase(formula.charAt(j))) {
                    j++;
                }
                String name = formula.substring(i, j);
                i = j;
                int val = 0;
                while (i < len && Character.isDigit(formula.charAt(i))) {
                    val = val * 10 + formula.charAt(i) - '0';
                    i++;
                }
                int count = val == 0 ? 1 : val;
                map.put(name, map.getOrDefault(name, 0) + count);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (String name : map.keySet()) {
            String s = map.get(name) == 1 ? name : name + map.get(name);
            sb.append(s);
        }
        
        return sb.toString();
    }
}
