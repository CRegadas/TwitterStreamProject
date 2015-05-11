package hello

import java.io.{OutputStream, ObjectOutputStream}

import com.fasterxml.jackson.databind.ObjectMapper
import kafka.serializer.Encoder
import kafka.utils.VerifiableProperties

class JsonEncoder(props: VerifiableProperties = null) extends Encoder[Object]
{
  override def toBytes(t: Object): Array[Byte] = {
    if(t == null)
      null
    else{
      val objectMapper = new ObjectMapper()
//this.objectMapper.setPropertyNamingStrategy(
//   PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES

      objectMapper.writeValueAsBytes(t)
    }

  }

}
