// Copyright (c) 2015 Picarro, Inc. All rights reserved 

/**
 * Communicates with host simulator which has an endpoint to which commands can be sent
 */

import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

class HostSim {
  //def config = new ConfigSlurper().parse(new File('src/test/resources/simConfig.groovy').toURL()) 
  //def client = new RESTClient(config.endpoint)
	def client = new RESTClient("http://localhost:8080")

  /**
   * Stops the Host simulator. This should probably be used only for creating 
   * abnormal test conditions.
   */
  def stop() {
    client.post(path: '/stop')
  }

  /**
   * Sends the replay definition to the simulator which attempt to create a replay
   * network and run it. 
   */
  def startReplay(replayDefn) {
    client.post(path: '/replay', body: replayDefn, requestContentType: URLENC)
  }

  /**
   * Stops current replay in the host simulator
   */
  def stopReplay() {
    client.post(path: '/replay/stop')
  }

  /**
   * Convenience method to replay a db3 file. The name of the file should in the standard
   * URL format. Some popular name patterns are listed here.
   *
   * file://hostname/path/to/the%20file.txt
   * file:///c:/path/to/the%20file.txt
   */
  def startReplayDb3File(db3FileName) {
    def defn = """node gps_reader target=dbreader.gps_reader out=gps_out args='${db3FileName}'
node anemometer_reader target=dbreader.anemometer_reader 
     out=anemometer_out args='${db3FileName}'
node meas_reader target=dbreader.measurement_reader 
     out=meas_out args='${db3FileName}'
node merger target=stream_merger.stream_merger 
     in=gps_out,anemometer_out,meas_out out=merge_out
node zmq_pub target=zmqsender.zmq_sender in=merge_out 
     args=True
"""
    client.post(path: '/replay', body: replayDefn, requestContentType: URLENC)
  }
}
