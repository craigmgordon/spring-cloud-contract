package com.example.creditcheckservice;

public class CreditCheckResponse {

		private Score score;
		
		public CreditCheckResponse(Score high) {
			score=high;
		}
		
		public Score getScore() {
			return score;
		}

		public void setScore(Score score) {
			this.score = score;
		}

		public enum Score{
			HIGH
		}
}
