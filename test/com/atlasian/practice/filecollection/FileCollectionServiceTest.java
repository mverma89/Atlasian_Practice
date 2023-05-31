package com.atlasian.practice.filecollection;

import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(JMockit.class)
public class FileCollectionServiceTest {

    @Test
    public void testTopKCollection(){

        FileCollectionService fileCollectionService = new FileCollectionService();

        File file1 = new File("File1", 100);
        File file2 = new File("File2", 100);
        File file3 = new File("File3", 100);
        File file4 = new File("File4", 100);
        File file5 = new File("File5", 100);
        File file6 = new File("File6", 100);
        File file7 = new File("File7", 100);

        fileCollectionService.addFile(file1,"COLL-1");
        fileCollectionService.addFile(file2,"COLL-1");
        fileCollectionService.addFile(file3,"COLL-2");
        fileCollectionService.addFile(file4,"COLL-3");
        fileCollectionService.addFile(file5,"COLL-2");
        fileCollectionService.addFile(file6,"COLL-3");
        fileCollectionService.addFile(file6,"COLL-2");

        new Verifications(){
            {
                List<String> topK = fileCollectionService.getTopKCollection(1);
                Assert.assertNotNull(topK);
                Assert.assertEquals(1, topK.size());
                Assert.assertEquals("COLL-2", topK.get(0));
            }
        };
    }
}