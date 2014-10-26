package com.github.domgold.doctools.asciidoctor.docx;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.extension.RubyExtensionRegistry;
import org.asciidoctor.extension.spi.ExtensionRegistry;

public class DocxBackendRegistry implements ExtensionRegistry {

	public DocxBackendRegistry()  {
		// TODO Auto-generated constructor stub
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.asciidoctor.extension.spi.ExtensionRegistry#register(org.asciidoctor
	 * .Asciidoctor)
	 */
	@Override
	public void register(Asciidoctor asciidoctor) {
		RubyExtensionRegistry rubyExtensionRegistry = asciidoctor
				.rubyExtensionRegistry();
		rubyExtensionRegistry
				.loadClass(
						this.getClass()
								.getResourceAsStream(
										"docxbackend.rb"));
	}
}
