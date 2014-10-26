package com.github.domgold.doctools.asciidoctor.docx;

import static org.asciidoctor.OptionsBuilder.options;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.SafeMode;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class DocxTest {

	private Asciidoctor asciidoctor;
	private Options options;
	private File destinationDir;

	@Before
	public void initAsciidoctor() throws IOException {
		asciidoctor = Asciidoctor.Factory.create();
		DocxBackendRegistry reg = new DocxBackendRegistry();
		reg.register(asciidoctor);
		destinationDir = new File("target/test-output");
		if (!destinationDir.exists() && !destinationDir.mkdirs()) {
			throw new IOException("could not create test-output dir");
		}
		options = options().toDir(destinationDir).safe(SafeMode.UNSAFE)
				.get();
	}

	@Test
	public void test() throws Docx4JException {
		options.setBackend("docx");
		asciidoctor.convertFile(new File("src/test/resources/simple_example.adoc"), options);
		File outfile = new File(destinationDir, "simple_example.docx");
		assertTrue(outfile.exists());
		WordprocessingMLPackage newerPackage = WordprocessingMLPackage.load(outfile);
		assertEquals("Simple example", newerPackage.getTitle());
	}

}
