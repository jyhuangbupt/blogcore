package com.henry.blog.util;

/**
 * 还款金额计算工具
 * @author haoyanmei
 * @since 2015-5-28
 */
public class RepaymentAmount {

	/**
	 * 等额本息
	 * @param money 本金
	 * @param rate 年利率
	 * @param termNum 期数
	 * @return
	 */
	public static long averageCapitalPlusInterest (long money, Float rate, int termNum) {
		float monRate = rate/12;
		float r = monRate/100;
		// 等额本息
		long repayMoney = (long)((money*r*(Math.pow(1+r,termNum))/(Math.pow(1+r,termNum)-1))*100);
		return repayMoney;
	}
	
	/**
	 * 等额本金
	 * @param money 本金
	 * @param rate 年利率
	 * @param termNum 期数
	 * @param sumAlr 已还总额
	 * @return
	 */
	public static long averageCapital(long money, Float rate, int termNum, long sumAlr) {
		// 求出月利率
		float monRate = rate/12;
		float r = monRate/100;
		// 等额本金
		long repayMoney = (long)(((money/termNum) + (money*100-sumAlr) * r/100)*100);
		return repayMoney;
	}
	
	/**
	 * 到期一次还本付息
	 * @param money 本金
	 * @param rate 年利率
	 * @param termNum 期数
	 * @return
	 */
	public static long DuePrimaryDebtService(long money, Float rate, int termNum) {
		// 求出月利率
		float monRate = rate/12;
		// 到期一次还本付息额=贷款本金×[1+年利率（%）] （贷款期为一年）；年利率=月利率*12
		// 到期一次还本付息额=贷款本金×[1+月利率（‰）×贷款期（月）] （贷款期不到一年）
		long repayMoney;
		float r = monRate/100;
		// 贷款期不到一年
		if(termNum <= 12) {
			repayMoney = (long)((money*(1 + r * termNum))*100);
		} else { // 贷款期为一年
			repayMoney = (long)((money*(1 + r * 12))*100);
		}
		return repayMoney;
	}
	
	/**
	 * 到期一次还本按季付息
	 * @param money 本金
	 * @param rate 年利率
	 * @param termNum 期数
	 * @return
	 */
	public static int debtQuarterInterestPayments(long money, float rate, int termNum) {
		// 一个季度的利息是本金*年利率*四分之一。贷款期限到了，本金和利息一起还清
		float r = rate/100;
		// 一个季度的利息
		int quarterRate = (int)((money*r)/4*100);
		return quarterRate;
	}
	
	/**
	 * 违约金
	 * @param refMoney 当期应还金额
	 * @param actualRef 实际还款金额
	 * @param day 逾期天数
	 * @param coefficient 违约金系数
	 * @return
	 */
	public static long coefficient(long refMoney,  long actualRef, long day, float coefficient) {
		// 违约金 = (月应还本金 + 月应还利息  – 实际还款金额) × 违约金系数 × 逾期天数，其中违约金系数为0.2% 
		float coe = coefficient/100;
		long coeMoney = (long)((refMoney - actualRef)*coe*day);
		return coeMoney;
	}
}
