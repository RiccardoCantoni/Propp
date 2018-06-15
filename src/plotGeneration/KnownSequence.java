package plotGeneration;

public enum KnownSequence {
	MAIN_SEQUENCE(
			new String[] {
				"ReconDelivery",
				"Villainy",
				"Lack",
				"MediationCounteraction",
				"FirstFunctionReaction",
				"Acquisition",
				"Guidance",
				"Struggle",
				"Branding",
				"Liquidation",
				"Return",
				"FalseClaimsFalseReward",
				"Recognition",
				"ExposurePunishment",
				"Reward"
			}),
	RETRY_RECON_SEQUENCE(
			new String[] {
					"Filler",
					"FirstFunctionReaction"
			}),
	RETRY_TEST_SEQUENCE(
			new String[] {
					"Filler",
					"FirstFunctionReaction"
				}),
	RETRY_STRUGGLE_SEQUENCE(
			new String[] {
					"Filler",
					"FirstFunctionReaction",
					"Acquisition",
					"Guidance",
					"Struggle"
				}),
	RETRY_GUIDANCE_SEQUENCE(
			new String[] {
					"Filler",
					"FirstFunctionReaction",
					"Acquisition",
					"Guidance"
				}
			),
	RETRY_RECOGNITION_SEQUENCE(
			new String[] {
					"Filler",
					"Return",
					"Recognition"
				}
			),
	QUEST_ITEM_SEQUENCE(
			new String[] {
					"Filler",
					"Lack",
					"FirstFunctionReaction",
					"Acquisition"
				}),
	QUEST_HELPER_SEQUENCE(
			new String[] {
					"Filler",
					"Lack",
					"FirstFunctionReaction",
					"Acquisition"
				}),
	IMPOSTURE_SEQUENCE(
			new String[] {
					"ReconDelivery",
					"FalseClaimsFalseReward",
					"Recognition",
					"ExposurePunishment",
					"Reward"
				});
	
	
	
	private final String[] sequence;
	
	KnownSequence(String[] sequence) {
		this.sequence = sequence;
	}
	
	public String[] getSequence() {
		return this.sequence;
	}
		
}
