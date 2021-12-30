package javaBeans;

import java.io.Serializable;

/**
 * テスト用
 */
public class TestJavaBeans implements Serializable {
	private String[] nums;
	private String numsForMakeDice;

	public TestJavaBeans(String[] nums) {
		this.nums = nums;
	}
	
	public TestJavaBeans(String numsForMakeDice) {
		this.numsForMakeDice = numsForMakeDice;
	}

	public String[] getNums() { return nums; }

	public void setNums(String[] nums) { this.nums = nums; }

	public String getNumsForMakeDice() { return numsForMakeDice; }

	public void setNumsForMakeDice(String numsForMakeDice) { this.numsForMakeDice = numsForMakeDice; }
	
}
