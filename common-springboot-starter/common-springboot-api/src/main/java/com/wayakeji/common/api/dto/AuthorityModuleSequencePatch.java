package com.wayakeji.common.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class AuthorityModuleSequencePatch {
	
	@NotNull
	@Size(min = 1)
	private List<AuthorityModuleSequence> sequences;

	public List<AuthorityModuleSequence> getSequences() {
		return sequences;
	}

	public void setSequences(List<AuthorityModuleSequence> sequences) {
		this.sequences = sequences;
	}
	
}
