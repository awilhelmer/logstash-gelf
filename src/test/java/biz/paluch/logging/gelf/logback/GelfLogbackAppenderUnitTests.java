package biz.paluch.logging.gelf.logback;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import biz.paluch.logging.RuntimeContainer;

/**
 * @author Mark Paluch
 */
public class GelfLogbackAppenderUnitTests {

    private static final String FACILITY = "facility";
    private static final String HOST = "host";
    private static final int GRAYLOG_PORT = 1;
    private static final int MAXIMUM_MESSAGE_SIZE = 1234;
    private static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss,SSSS";

    @Test
    public void testSameFieldsGelfLogbackAppender() {
        GelfLogbackAppender sut = new GelfLogbackAppender();

        sut.setAdditionalFields("");
        sut.setExtractStackTrace("true");
        sut.setFacility(FACILITY);
        sut.setFilterStackTrace(true);
        sut.setGraylogHost(HOST);
        sut.setGraylogPort(GRAYLOG_PORT);
        sut.setMaximumMessageSize(MAXIMUM_MESSAGE_SIZE);
        sut.setDynamicMdcFields(".*");
        sut.setIncludeFullMdc(true);
        sut.setMdcFields("");
        sut.setMdcProfiling(true);

        assertEquals(FACILITY, sut.getFacility());
        assertEquals(HOST, sut.getGraylogHost());
        assertEquals(HOST, sut.getHost());
        assertEquals(GRAYLOG_PORT, sut.getPort());
        assertEquals(GRAYLOG_PORT, sut.getGraylogPort());
        assertEquals(MAXIMUM_MESSAGE_SIZE, sut.getMaximumMessageSize());
        assertEquals(TIMESTAMP_PATTERN, sut.getTimestampPattern());
        assertEquals(RuntimeContainer.FQDN_HOSTNAME, sut.getOriginHost());

        assertEquals("true", sut.getExtractStackTrace());
        assertTrue(sut.isFilterStackTrace());
        assertTrue(sut.isIncludeFullMdc());
        assertTrue(sut.isMdcProfiling());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPort() throws Exception {

        GelfLogbackAppender sut = new GelfLogbackAppender();
        sut.setPort(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMaximumMessageSize() throws Exception {

        GelfLogbackAppender sut = new GelfLogbackAppender();
        sut.setMaximumMessageSize(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidVersion() throws Exception {

        GelfLogbackAppender sut = new GelfLogbackAppender();
        sut.setVersion("7");
    }
}
