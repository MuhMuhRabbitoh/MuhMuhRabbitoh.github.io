package masterThesis;


public enum Group {Group1 (0), Group2 (1);

private int group;

	Group(int group) {
		this.group= group;

	}

	public int getGroup() {
		return this.group;
	}
}
