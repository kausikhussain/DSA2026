package Day05_BinarySearch;

public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        // Range of eating speed: 1 to max(piles)
        int left = 1;
        int right = 1;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        
        int result = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long hoursNeeded = 0;
            
            // Calculate total hours to eat all bananas at speed 'mid'
            for (int pile : piles) {
                hoursNeeded += Math.ceil((double) pile / mid);
            }
            
            if (hoursNeeded <= h) {
                // If she can finish, try a slower speed (smaller 'k')
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                // If she can't finish, she needs to eat faster
                left = mid + 1;
            }
        }
        
        return result;
    }
}
