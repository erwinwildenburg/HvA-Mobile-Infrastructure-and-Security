using System;
using Microsoft.WindowsAzure.Storage.Table;

namespace API.Models
{
    public class Status : TableEntity
    {
        public Status(string androidUid)
        {
            this.PartitionKey = androidUid;
            this.RowKey = DateTime.Now.ToString();
        }

        public Status() { }
        public string Longitude { get; set; }
        public string Latitude { get; set; }
        public string Altitude { get; set; }
        public string Speed { get; set; }
        public string Steps { get; set; }
    }
}