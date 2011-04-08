/*
 * Copyright (c) 2008. All rights reserved.
 */
package ro.isdc.wro.extensions.processor;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import ro.isdc.wro.extensions.processor.js.YUIJsCompressorProcessor;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.util.WroTestUtils;
import ro.isdc.wro.util.WroUtil;


/**
 * Test YUI js compressor processor.
 *
 * @author Alex Objelean
 * @created Created on Nov 28, 2008
 */
public class TestYUIJsCompressorProcessor {
  @Test
  public void testNoMunge() throws IOException {
    final ResourcePostProcessor processor = YUIJsCompressorProcessor.noMungeCompressor();
    final URL url = getClass().getResource("yui");

    final File testFolder = new File(url.getFile(), "test");
    final File expectedFolder = new File(url.getFile(), "expectedNomunge");
    WroTestUtils.compareFromDifferentFoldersByExtension(testFolder, expectedFolder, "js",
      WroUtil.newResourceProcessor(processor));
  }

  @Test
  public void testMunge() throws IOException {
    final ResourcePostProcessor processor = YUIJsCompressorProcessor.noMungeCompressor();
    final URL url = getClass().getResource("yui");

    final File testFolder = new File(url.getFile(), "test");
    final File expectedFolder = new File(url.getFile(), "expectedMunge");
    WroTestUtils.compareFromDifferentFoldersByExtension(testFolder, expectedFolder, "js",
      WroUtil.newResourceProcessor(processor));
  }

  @Test
  public void testInvalidJsShouldBeUnchanged()
    throws IOException {
    final ResourcePostProcessor processor = YUIJsCompressorProcessor.doMungeCompressor();
    final String resourceUri = "classpath:" + WroUtil.toPackageAsFolder(getClass()) + "/invalid.js";
    WroTestUtils.compareProcessedResourceContents(resourceUri, resourceUri, WroUtil.newResourceProcessor(processor));
  }
}
