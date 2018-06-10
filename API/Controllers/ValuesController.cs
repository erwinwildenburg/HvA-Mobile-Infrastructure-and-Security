using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using API.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.WindowsAzure.Storage;
using Microsoft.WindowsAzure.Storage.Table;

namespace API.Controllers
{
    [Route("api/status")]
    public class ValuesController : Controller
    {
        private CloudStorageAccount storageAccount = null;
        private CloudTableClient tableClient = null;
        private CloudTable table = null;

        public ValuesController()
        {
            this.storageAccount = CloudStorageAccount.Parse("DefaultEndpointsProtocol=https;AccountName=hvainnovation;AccountKey=gw6/ztCkRTWd3pLT+UpYchvvDh7nq8VQigRRbwoSuQ/+WouMOb2OqF90oE5N/ewTgUX3us9Eu2UjVJyInbyhwA==;EndpointSuffix=core.windows.net");
            this.tableClient = storageAccount.CreateCloudTableClient();
            this.table = tableClient.GetTableReference("status");
        }

        /// <summary>
        /// Returns a list of status
        /// </summary>
        /// <response code="200">OK</response>
        [HttpGet("{id}")]
        [Produces(typeof(List<Status>))]
        public async Task<IActionResult> GetById(string id)
        {
            List<Status> result = new List<Status>();
            TableQuery<Status> rangeQuery = new TableQuery<Status>().Where(
                TableQuery.GenerateFilterCondition("PartitionKey", QueryComparisons.Equal, id.ToString()
            ));
            TableContinuationToken tableContinuationToken = null;
            do
            {
                TableQuerySegment<Status> queryResponse = await table.ExecuteQuerySegmentedAsync<Status>(rangeQuery, tableContinuationToken, null, null);
                tableContinuationToken = queryResponse.ContinuationToken;
                result.AddRange(queryResponse.Results);
            }
            while(tableContinuationToken != null);

            return Ok(result.Where(x => x.PartitionKey.Equals(id.ToString())));
        }

        /// <summary>
        /// Puts a new status in the database
        /// </summary>
        /// <response code="200">OK</response>
        [HttpPost]
        public async Task<IActionResult> Post([FromBody]Status value)
        {
            TableOperation insertOperation = TableOperation.Insert(value);
            await table.ExecuteAsync(insertOperation);

            return Ok();
        }
    }
}
